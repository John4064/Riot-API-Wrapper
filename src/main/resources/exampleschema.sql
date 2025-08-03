
drop table if exists Match;
drop table if exists Info;
drop table if exists Metadata;


-- 2. Info Table (match details excluding arrays)
CREATE TABLE Info (
    info_id BIGSERIAL PRIMARY KEY,
    end_of_game_result VARCHAR,
    game_creation BIGINT ,
    game_duration BIGINT ,
    game_end_timestamp BIGINT ,
    game_id BIGINT ,
    game_mode VARCHAR ,
    game_name VARCHAR ,
    game_start_timestamp BIGINT ,
    game_type VARCHAR ,
    game_version VARCHAR ,
    map_id INTEGER ,
    participants JSONB NOT NULL,   -- Stores array of ParticipantDto as JSONB
    platform_id VARCHAR ,
    queue_id INTEGER ,
    teams JSONB NOT NULL,
    tournament_code VARCHAR
-- teams and participants referenced in child tables
);

-- 3. Metadata Table
CREATE TABLE Metadata (
      metadata_id BIGSERIAL PRIMARY KEY,
      data_version VARCHAR NOT NULL,
      match_id VARCHAR NOT NULL
-- participants as join table below
);

-- 1. Match Table (root container)
CREATE TABLE Match (
   match_id BIGSERIAL PRIMARY KEY,
   info_id BIGINT NOT NULL REFERENCES Info(info_id),
   metadata_id BIGINT NOT NULL REFERENCES Metadata(metadata_id),
   FOREIGN KEY (info_id) REFERENCES Info(info_id),
   FOREIGN KEY (metadata_id) REFERENCES Metadata(metadata_id)
);


--
--
--  -- 4. Metadata Participants (primitive list of strings)
--  CREATE TABLE Metadata_Participant (
--                                            metadata_id BIGINT NOT NULL REFERENCES Metadata(id),
--                                            participant_identifier VARCHAR NOT NULL,
--                                            PRIMARY KEY (metadata_id, participant_identifier)
--      );
--
--
--  -- 5. Team Table
--  CREATE TABLE Team (
--                            id BIGSERIAL PRIMARY KEY,
--                            info_id BIGINT NOT NULL REFERENCES Info(id),
--                            team_id INTEGER NOT NULL,
--                            win BOOLEAN NOT NULL,
--          -- Optional feats & objectives could be JSONB or separate tables
--                            feats JSONB,
--                            objectives JSONB
--      );
--
--
--  -- 6. Team_Ban Table (for TeamDto.bans array)
--  CREATE TABLE Team_Ban (
--                                id BIGSERIAL PRIMARY KEY,
--                                team_id BIGINT NOT NULL REFERENCES Team(id),
--                                champion_id INTEGER,
--                                pick_turn INTEGER
--      );
--
--
--  -- 7. Participant Table
--  CREATE TABLE Participant (
--                                   id BIGSERIAL PRIMARY KEY,
--                                   info_id BIGINT NOT NULL REFERENCES Info(id),
--
--                                   participant_id INTEGER,
--                                   puuid VARCHAR NOT NULL,
--                                   summoner_id VARCHAR NOT NULL,
--                                   summoner_name VARCHAR NOT NULL,
--                                   champion_id INTEGER,
--                                   champion_name VARCHAR NOT NULL,
--                                   champion_transform INTEGER,
--                                   win BOOLEAN NOT NULL,
--
--          -- Basic integer and boolean fields (abbreviated example - expand all required fields)
--                                   assist_me_pings INTEGER,
--                                   assists INTEGER,
--                                   bait_pings INTEGER,
--                                   baron_kills INTEGER,
--          -- Add all other ParticipantDto primitive fields here similarly:
--                                   lane VARCHAR,
--                                   role VARCHAR,
--                                   team_position VARCHAR,
--                                   team_id INTEGER,
--
--          -- Nested or complex objects stored as JSONB (optional approach)
--                                   challenges JSONB,
--                                   missions JSONB,
--                                   perks JSONB,
--
--          -- Player augment fields, scores, spells, vision stats, etc., should also be added here
--          -- (Due to size, include fields as per your preference)
--          -- Example:
--                                   gold_earned INTEGER,
--                                   deaths INTEGER,
--                                   kills INTEGER,
--                                   win BOOLEAN
--      );
--
--
--  -- 8. Info_Team Join Table (Info -> Team 1:M)
--  CREATE TABLE Info_Team (
--                                 info_id BIGINT NOT NULL REFERENCES Info(id),
--                                 team_id BIGINT NOT NULL REFERENCES Team(id),
--                                 PRIMARY KEY (info_id, team_id)
--      );
--
--
--  -- 9. Info_Participant Join Table (Info -> Participant 1:M)
--  CREATE TABLE Info_Participant (
--                                        info_id BIGINT NOT NULL REFERENCES Info(id),
--                                        participant_id BIGINT NOT NULL REFERENCES Participant(id),
--                                        PRIMARY KEY (info_id, participant_id)
--      );
