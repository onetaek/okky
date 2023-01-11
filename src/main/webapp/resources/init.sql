drop schema okky;
create schema okky;

CREATE TABLE `okky`.`boards`
(
    `id`   VARCHAR(10) NOT NULL,
    `text` VARCHAR(20) NOT NULL,
    CONSTRAINT PRIMARY KEY (`id`)
)engine = InnoDB default charset=utf8;

CREATE TABLE `okky`.`tags`
(
    `value` VARCHAR(20) NOT NULL,
    `text`  VARCHAR(20) NOT NULL,
    CONSTRAINT PRIMARY KEY (`value`)
)engine = InnoDB default charset=utf8;

CREATE TABLE `okky`.`contactCountries`
(
    `value` VARCHAR(3)  NOT NULL,
    `text`  VARCHAR(20) NOT NULL,
    CONSTRAINT PRIMARY KEY (`value`)
)engine = InnoDB default charset=utf8;

CREATE TABLE `okky`.`telecoms`
(
    `value` VARCHAR(10) NOT NULL, #SKT, KT, LGU, 알뜰폰
            `text`  VARCHAR(20) NOT NULL,
    CONSTRAINT PRIMARY KEY (`value`)
)engine = InnoDB default charset=utf8;

CREATE TABLE `okky`.`statuses`
(
    `value` VARCHAR(3)  NOT NULL, # OKY , SUS, RES
            `text`  VARCHAR(20) NOT NULL,
    CONSTRAINT PRIMARY KEY (`value`)
)engine = InnoDB default charset=utf8;

CREATE TABLE `okky`.`contactAuth`
(
    `index`     INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `contact`   VARCHAR(20)  NOT NULL,
    `code`      VARCHAR(6)   NOT NULL,
    `salt`      VARCHAR(128) NOT NULL,
    `createdAt` DATETIME     NOT NULL DEFAULT NOW(),
    `expiresAt` DATETIME     NOT NULL,
    `isExpired` BOOLEAN      NOT NULL DEFAULT FALSE,
    CONSTRAINT PRIMARY KEY (`index`)
)engine = InnoDB default charset=utf8;

CREATE TABLE `okky`.`emailAuth`
(
    `index`     INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `email`     VARCHAR(100)  NOT NULL,
    `code`      VARCHAR(6)   NOT NULL,
    `createdAt` DATETIME     NOT NULL DEFAULT NOW(),
    `expiresAt` DATETIME     NOT NULL,
    `isExpired` BOOLEAN      NOT NULL DEFAULT FALSE,
    CONSTRAINT PRIMARY KEY (`index`)
)engine = InnoDB default charset=utf8;


CREATE TABLE `okky`.`users`
(
    `email`             VARCHAR(100) NOT NULL,
    `password`          VARCHAR(128) NOT NULL,
    `name`              VARCHAR(20)  NOT NULL,
    `nickName`          VARCHAR(20)  NOT NULL UNIQUE,
    `telecom`           VARCHAR(10)  NOT NULL, # 0FK
        `contactCountryValue` VARCHAR(3)   NOT NULL, # 082, 0FK
                        `contact`           VARCHAR(20)  NOT NULL,
    `policyEmailSend`   boolean      NOT NULL DEFAULT FALSE,
    `createdAt`         DATETIME     NOT NULL DEFAULT NOW(),
    `statusValue`       VARCHAR(3)   NOT NULL, #정상 회원, 일시 정지 회원, 탈퇴 회원 등....  0FK
        `isAdmin`           boolean      NOT NULL DEFAULT FALSE,

    CONSTRAINT PRIMARY KEY (`email`),
    CONSTRAINT UNIQUE (`telecom`, `contact`, `contactCountryValue`),
    CONSTRAINT FOREIGN KEY (`contactCountryValue`) REFERENCES `okky`.`contactCountries` (`value`)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (`telecom`) REFERENCES `okky`.`telecoms` (`value`)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (`statusValue`) REFERENCES `okky`.`statuses` (`value`)
        ON UPDATE CASCADE
        ON DELETE CASCADE
)engine = InnoDB default charset=utf8;

CREATE TABLE `okky`.`articles`
(
    `index`     INT UNSIGNED    NOT NULL AUTO_INCREMENT,
    `boardId`   VARCHAR(10)     NOT NULL, #
                `tag`       VARCHAR(20)     NOT NULL,
    `userEmail` VARCHAR(100)    NOT NULL,
    `title`     VARCHAR(100)    NOT NULL,
    `content`   VARCHAR(5000)   NOT NULL,
    `view`      BIGINT UNSIGNED NOT NULL DEFAULT 0,
    `createdAt` DATETIME        NOT NULL DEFAULT NOW(),
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT FOREIGN KEY (`boardId`) REFERENCES `okky`.`boards` (`id`)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (`userEmail`) REFERENCES `okky`.`users` (`email`)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (`tag`) REFERENCES `okky`.`tags` (`value`)
        ON UPDATE CASCADE
        ON DELETE CASCADE
)engine = InnoDB default charset=utf8;

CREATE TABLE `okky`.`articleLikes`
(
    `userEmail`    VARCHAR(100) NOT NULL,
    `articleIndex` INT UNSIGNED NOT NULL,
    `createdAt`    DATETIME     NOT NULL DEFAULT NOW(),
    CONSTRAINT PRIMARY KEY (`userEmail`, `articleIndex`),
    CONSTRAINT FOREIGN KEY (`userEmail`) REFERENCES `okky`.`users` (`email`)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (`articleIndex`) REFERENCES `okky`.`articles` (`index`)
        ON UPDATE CASCADE
        ON DELETE CASCADE
)engine = InnoDB default charset=utf8;

CREATE TABLE `okky`.`comments`
(
    `index`        INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `commentIndex` INT UNSIGNED NULL     DEFAULT NULL,
    `articleIndex` INT UNSIGNED NOT NULL,
    `userEmail`    VARCHAR(100) NOT NULL,
    `content`      VARCHAR(200) NOT NULL,
    `createdAt`    DATETIME     NOT NULL DEFAULT NOW(),
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT FOREIGN KEY (`commentIndex`) REFERENCES `okky`.`comments` (`index`)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (`articleIndex`) REFERENCES `okky`.`articles` (`index`)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (`userEmail`) REFERENCES `okky`.`users` (`email`)
        ON UPDATE CASCADE
        ON DELETE CASCADE
)engine = InnoDB default charset=utf8;





insert into `okky`.`telecoms` (value, text) values ('KT','KT');
insert into `okky`.`telecoms` (value, text) values ('LGU+','LGU+');
insert into `okky`.`telecoms` (value, text) values ('SKT','SKT');

insert into `okky`.`contactCountries` (value, text) values ('082','대한민국');

insert into `okky`.`boards` (id,text) values('1','Q&A');
insert into `okky`.`boards` (id,text) values ('2','커뮤니티');
insert into `okky`.`boards` (id,text) values('3','공지사항');

