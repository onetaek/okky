#board 더미 데이터 만들기
DELIMITER $$
DROP PROCEDURE IF EXISTS insertLoop$$

CREATE PROCEDURE insertLoop()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 200 DO
            INSERT INTO `okky`.articles( boardId, userEmail, title, content)
            VALUES (2,concat('kjh65659172@gmail.com'),concat('더미 제목',i),concat('더미 내용',i));
            SET i = i + 1;
END WHILE;
END$$
DELIMITER $$

CALL insertLoop;
$$