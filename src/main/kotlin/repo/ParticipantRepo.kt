package repo


import entities.Participant
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

class ParticipantRepo {

    fun insertParticipant(newMetadataId: Long, newParticipantIdentifier: String) =transaction{
        Participant.insert { row ->
            row[metadataId]= newMetadataId
            row[participantIdentifier] = newParticipantIdentifier
        }
    }
}