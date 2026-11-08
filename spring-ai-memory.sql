CREATE TABLE `spring_ai_chat_memory` (
                                         `conversation_id` varchar(36) CHARACTER SET utf8mb4 NOT NULL,
                                         `content` text CHARACTER SET utf8mb4 NOT NULL,
                                         `type` enum('USER','ASSISTANT','SYSTEM','TOOL') CHARACTER SET utf8mb4 NOT NULL,
                                         `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                         KEY `SPRING_AI_CHAT_MEMORY_CONVERSATION_ID_TIMESTAMP_IDX` (`conversation_id`,`timestamp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci
;