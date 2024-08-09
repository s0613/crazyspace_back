package com.crazyspace_edu.api.repository.conversation;

import com.crazyspace_edu.api.domain.ai.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long>, ConversationRepositoryCustom {

}
