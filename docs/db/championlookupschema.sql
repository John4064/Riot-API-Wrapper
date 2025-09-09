-- Lookup Tables

-- See champion_key_int.json for raw json data example

-- Core champion table
CREATE TABLE champions (
                           champion_id SERIAL PRIMARY KEY,
                           key INT UNIQUE NOT NULL,
                           version TEXT NOT NULL,
                           id TEXT UNIQUE NOT NULL,
                           name TEXT NOT NULL,
                           title TEXT,
                           blurb TEXT,
                           partype TEXT
);

-- Info (difficulty/role scores)
CREATE TABLE champion_info (
                               champion_id INT PRIMARY KEY REFERENCES champions(champion_id) ON DELETE CASCADE,
                               attack INT,
                               defense INT,
                               magic INT,
                               difficulty INT
);

-- Image metadata
CREATE TABLE champion_images (
                                 champion_id INT PRIMARY KEY REFERENCES champions(champion_id) ON DELETE CASCADE,
                                 "full" TEXT,
                                 sprite TEXT,
                                 img_group TEXT,
                                 x INT,
                                 y INT,
                                 w INT,
                                 h INT
);

-- Tags (many-to-many: Fighter, Mage, Assassin, etc.)
CREATE TABLE tags (
                      tag_id SERIAL PRIMARY KEY,
                      name TEXT UNIQUE NOT NULL
);

CREATE TABLE champion_tags (
                               champion_id INT REFERENCES champions(champion_id) ON DELETE CASCADE,
                               tag_id INT REFERENCES tags(tag_id) ON DELETE CASCADE,
                               PRIMARY KEY (champion_id, tag_id)
);

-- Stats (numbers like hp, mp, armor, etc.)
CREATE TABLE champion_stats (
                                champion_id INT PRIMARY KEY REFERENCES champions(champion_id) ON DELETE CASCADE,
                                hp NUMERIC,
                                hpperlevel NUMERIC,
                                mp NUMERIC,
                                mpperlevel NUMERIC,
                                movespeed NUMERIC,
                                armor NUMERIC,
                                armorperlevel NUMERIC,
                                spellblock NUMERIC,
                                spellblockperlevel NUMERIC,
                                attackrange NUMERIC,
                                hpregen NUMERIC,
                                hpregenperlevel NUMERIC,
                                mpregen NUMERIC,
                                mpregenperlevel NUMERIC,
                                crit NUMERIC,
                                critperlevel NUMERIC,
                                attackdamage NUMERIC,
                                attackdamageperlevel NUMERIC,
                                attackspeedperlevel NUMERIC,
                                attackspeed NUMERIC
);





--Staging Table
drop table if exists champion_raw;
CREATE TABLE champion_raw (
                              data jsonb
);



-- Import JSON file into champion_raw.data using DataGrip or psql:
-- \COPY champion_raw(data) FROM '/Users/jpark/Downloads/champion.json';

-- Champ Insert
INSERT INTO champions (key, version, id, name, title, blurb, partype)
SELECT
    (data ->> 'key')::integer,
    data ->> 'version',
    data ->> 'id',
    data->>'name',
    data->>'title',
    data->>'blurb',
    data->>'partype'
FROM champion_raw;

-- Champ Info Insert
INSERT INTO champion_info (champion_id, attack, defense, magic, difficulty)
SELECT c.champion_id,
       (data->'info'->>'attack')::INT,
       (data->'info'->>'defense')::INT,
       (data->'info'->>'magic')::INT,
       (data->'info'->>'difficulty')::INT
FROM champion_raw JOIN champions c ON c.id = data->>'id';

-- Champion Images
INSERT INTO champion_images (champion_id, "full", sprite, img_group, x, y, w, h)
SELECT c.champion_id,
       data->'image'->>'full',
       data->'image'->>'sprite',
       data->'image'->>'group',
       (data->'image'->>'x')::INT,
       (data->'image'->>'y')::INT,
       (data->'image'->>'w')::INT,
       (data->'image'->>'h')::INT
FROM champion_raw JOIN champions c ON c.id = data->>'id';

-- First insert unique tags
INSERT INTO tags (name)
SELECT DISTINCT jsonb_array_elements_text(data->'tags')
FROM champion_raw
ON CONFLICT (name) DO NOTHING;

-- Then link champions to tags
INSERT INTO champion_tags (champion_id, tag_id)
SELECT
    c.champion_id,
    t.tag_id
FROM champion_raw cr
         CROSS JOIN LATERAL jsonb_array_elements_text(cr.data->'tags') AS tag(tag_name)
         JOIN champions c
              ON c.key = (cr.data->>'key')::INTEGER
         JOIN tags t
              ON t.name = tag.tag_name
ON CONFLICT DO NOTHING;

-- Champ Stats
INSERT INTO champion_stats (
    champion_id, hp, hpperlevel, mp, mpperlevel, movespeed,
    armor, armorperlevel, spellblock, spellblockperlevel,
    attackrange, hpregen, hpregenperlevel, mpregen, mpregenperlevel,
    crit, critperlevel, attackdamage, attackdamageperlevel,
    attackspeedperlevel, attackspeed
)
SELECT c.champion_id,
       (data->'stats'->>'hp')::NUMERIC,
       (data->'stats'->>'hpperlevel')::NUMERIC,
       (data->'stats'->>'mp')::NUMERIC,
       (data->'stats'->>'mpperlevel')::NUMERIC,
       (data->'stats'->>'movespeed')::NUMERIC,
       (data->'stats'->>'armor')::NUMERIC,
       (data->'stats'->>'armorperlevel')::NUMERIC,
       (data->'stats'->>'spellblock')::NUMERIC,
       (data->'stats'->>'spellblockperlevel')::NUMERIC,
       (data->'stats'->>'attackrange')::NUMERIC,
       (data->'stats'->>'hpregen')::NUMERIC,
       (data->'stats'->>'hpregenperlevel')::NUMERIC,
       (data->'stats'->>'mpregen')::NUMERIC,
       (data->'stats'->>'mpregenperlevel')::NUMERIC,
       (data->'stats'->>'crit')::NUMERIC,
       (data->'stats'->>'critperlevel')::NUMERIC,
       (data->'stats'->>'attackdamage')::NUMERIC,
       (data->'stats'->>'attackdamageperlevel')::NUMERIC,
       (data->'stats'->>'attackspeedperlevel')::NUMERIC,
       (data->'stats'->>'attackspeed')::NUMERIC
FROM champion_raw
         JOIN champions c ON c.id = data->>'id';

create view champion_card AS(
select c.name, version, title, blurb, (CASE WHEN difficulty =10 THEN 'Impossible'  WHEN difficulty =1 THEN 'Beginner'
                                           WHEN difficulty < 10 and difficulty>7 THEN 'Hard'
                                           WHEN difficulty > 1 and difficulty<4 THEN 'Easy' else 'Normal' END) as difficulty,
    string_agg(t.name, ',') as tags, im."full" as image_name from champions c
    join champion_info ci on ci.champion_id=c.champion_id
    join champion_tags ct on ct.champion_id=c.champion_id
    join tags t on ct.tag_id=t.tag_id
    join champion_images im on im.champion_id=c.champion_id
group by c.champion_id,ci.champion_id,im.champion_id
order by c.champion_id);