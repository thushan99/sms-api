CREATE TABLE `teacher` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `experience` longtext,
                           `address` varchar(255) DEFAULT NULL,
                           `created_at` datetime(6) DEFAULT NULL,
                           `date` date DEFAULT NULL,
                           `education` longtext,
                           `email` varchar(255) DEFAULT NULL,
                           `gender` enum('FEMALE','MALE') DEFAULT NULL,
                           `image_url` varchar(255) DEFAULT NULL,
                           `mobile` int DEFAULT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           `nic` varchar(255) DEFAULT NULL,
                           `subject` varchar(255) DEFAULT NULL,
                           `updated_at` datetime(6) DEFAULT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

CREATE TABLE `grade` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `teacher_id` bigint DEFAULT NULL,
                         `class_fee` double DEFAULT NULL,
                         `grade_name` varchar(255) DEFAULT NULL,
                         `grade` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         KEY `FK28f8ba9n0feejnamfay479ae1` (`teacher_id`),
                         CONSTRAINT `FK28f8ba9n0feejnamfay479ae1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `parent` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `address` varchar(255) DEFAULT NULL,
                          `guardian_type` enum('FATHER','GUARDIAN','MOTHER') DEFAULT NULL,
                          `mobile` int DEFAULT NULL,
                          `monthly_avg_income` double DEFAULT NULL,
                          `name` varchar(255) DEFAULT NULL,
                          `nic` varchar(255) DEFAULT NULL,
                          `occupation` varchar(255) DEFAULT NULL,
                          `religion` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `student` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `address` varchar(255) DEFAULT NULL,
                           `admission_date` date DEFAULT NULL,
                           `birth_date` date DEFAULT NULL,
                           `gender` enum('FEMALE','MALE') DEFAULT NULL,
                           `profile_image_url` varchar(255) DEFAULT NULL,
                           `additional_info` varchar(255) DEFAULT NULL,
                           `name` varchar(255) DEFAULT NULL,
                           `registration_number` varchar(255) DEFAULT NULL,
                           `religion` varchar(255) DEFAULT NULL,
                           `birth_certificate_url` varchar(255) DEFAULT NULL,
                           `grade_id` bigint DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FK4xvaqcll34afqdd9vkydid5qo` (`grade_id`),
                           CONSTRAINT `FK4xvaqcll34afqdd9vkydid5qo` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `attendance` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `student_id` bigint DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              KEY `FKnq6vm31it076obtjf2qp5coim` (`student_id`),
                              CONSTRAINT `FKnq6vm31it076obtjf2qp5coim` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `student_parent` (
                                  `student_id` bigint NOT NULL,
                                  `parent_id` bigint NOT NULL,
                                  KEY `FK1uqsk99lie7damnsh9osouodd` (`parent_id`),
                                  KEY `FK3nulmrwg4cubngtp7nq5lud86` (`student_id`),
                                  CONSTRAINT `FK1uqsk99lie7damnsh9osouodd` FOREIGN KEY (`parent_id`) REFERENCES `parent` (`id`),
                                  CONSTRAINT `FK3nulmrwg4cubngtp7nq5lud86` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `student_extra_activity` (
                                          `id` bigint NOT NULL AUTO_INCREMENT,
                                          `extra_activity` longtext,
                                          `student_id` bigint DEFAULT NULL,
                                          PRIMARY KEY (`id`),
                                          KEY `FKsxtjmawuvnkaljx7vi4ci9qyj` (`student_id`),
                                          CONSTRAINT `FKsxtjmawuvnkaljx7vi4ci9qyj` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `student_health` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `health_status` longtext,
                                  `student_id` bigint DEFAULT NULL,
                                  PRIMARY KEY (`id`),
                                  KEY `FKeh0hse0e8rssw7ybn84gilpcu` (`student_id`),
                                  CONSTRAINT `FKeh0hse0e8rssw7ybn84gilpcu` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `student_leadership` (
                                      `id` bigint NOT NULL AUTO_INCREMENT,
                                      `leadership` longtext,
                                      `student_id` bigint DEFAULT NULL,
                                      PRIMARY KEY (`id`),
                                      KEY `FK8yocpqu2w41atqtqqygi169f3` (`student_id`),
                                      CONSTRAINT `FK8yocpqu2w41atqtqqygi169f3` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `student_previous_school` (
                                           `id` bigint NOT NULL AUTO_INCREMENT,
                                           `school_name` longtext,
                                           `student_id` bigint DEFAULT NULL,
                                           PRIMARY KEY (`id`),
                                           KEY `FKiqeemdj1nqjlu20865jesmny8` (`student_id`),
                                           CONSTRAINT `FKiqeemdj1nqjlu20865jesmny8` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

