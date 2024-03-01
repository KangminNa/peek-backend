-- numbering 테이블을 초기화합니다.
DELETE FROM numbering;
DELETE FROM user;


INSERT INTO numbering (numbering, is_used) VALUES
                                               ('number1', 0),
                                               ('number2', 0),
                                               ('number3', 0),
                                               ('number4', 0),
                                               ('number5', 0),
                                               ('number6', 0),
                                               ('number7', 0),
                                               ('number8', 0),
                                               ('number9', 0),
                                               ('number10', 0);

