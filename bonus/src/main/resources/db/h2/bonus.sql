CREATE TABLE BONUS_PROGRAM(
	ID INT NOT NULL  AUTO_INCREMENT
	, DESCRIPTION VARCHAR(512) NOT NULL
	, PERCENT NUMBER NOT NULL
	, CAN_BE_CHANGED INT DEFAULT 0
	, PRIMARY KEY (ID)
);

CREATE TABLE PROGRAM_PARTNER_STATE(
    ID VARCHAR(20) NOT NULL
  , DESCRIPTION VARCHAR(512)
  , PRIMARY KEY (ID)
);

INSERT INTO PROGRAM_PARTNER_STATE (ID) VALUES ('ACTIVE');
INSERT INTO PROGRAM_PARTNER_STATE (ID) VALUES ('WAITED');
INSERT INTO PROGRAM_PARTNER_STATE (ID) VALUES ('CANCELLED');

CREATE TABLE PROGRAM_PARTNER(
	ID INT NOT NULL  AUTO_INCREMENT
	, PROGRAM INT NOT NULL
	, PARTNER INT NOT NULL
  , BY_DEFAULT INT DEFAULT 0
  , STATE_ID VARCHAR(20) NOT NULL
	, PRIMARY KEY (ID)
  , CONSTRAINT FK_PROGRAM_PARTNER_1 FOREIGN KEY (PROGRAM) REFERENCES BONUS_PROGRAM(ID)  ON DELETE CASCADE
  , CONSTRAINT FK_PROGRAM_PARTNER_2 FOREIGN KEY (PARTNER) REFERENCES PARTNER(ID)  ON DELETE CASCADE
  , CONSTRAINT FK_PROGRAM_PARTNER_3 FOREIGN KEY (STATE_ID) REFERENCES PROGRAM_PARTNER_STATE(ID)  ON DELETE CASCADE
);

CREATE TABLE PAYMENT_BONUS(
	ID INT NOT NULL AUTO_INCREMENT
	, PAYMENT INT NOT NULL
	, PARTNER INT NOT NULL
	, PROGRAM INT NOT NULL
	, TRANSACTION INT
  , AMOUNT_VALUE NUMBER NOT NULL
  , AMOUNT_CURRENCY VARCHAR(5) NOT NULL
	, PRIMARY KEY (ID)
  , CONSTRAINT FK_PAYMENT_BONUS_1 FOREIGN KEY (PAYMENT) REFERENCES PAYMENT(ID)  ON DELETE CASCADE
  , CONSTRAINT FK_PAYMENT_BONUS_2 FOREIGN KEY (PARTNER) REFERENCES PARTNER(ID)  ON DELETE CASCADE
  , CONSTRAINT FK_PAYMENT_BONUS_3 FOREIGN KEY (TRANSACTION) REFERENCES TXN(ID)  ON DELETE CASCADE
  , CONSTRAINT FK_PAYMENT_BONUS_4 FOREIGN KEY (PROGRAM) REFERENCES BONUS_PROGRAM(ID)  ON DELETE CASCADE
);
