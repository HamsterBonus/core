
CREATE TABLE BALANCE (
    ID INT NOT NULL  AUTO_INCREMENT
    , AMOUNT_VALUE NUMBER NOT NULL
    , HOLD_AMOUNT_VALUE NUMBER
    , AMOUNT_CURRENCY VARCHAR(5) NOT NULL
    , PRIMARY KEY (ID)
);

CREATE TABLE PARTNER (
    ID INT NOT NULL  AUTO_INCREMENT
    , NAME VARCHAR(50) NOT NULL
    , PRIMARY KEY (ID)
);

CREATE TABLE PARTNER_BALANCE (
    ID INT NOT NULL  AUTO_INCREMENT
    , PARTNER INT NOT NULL
    , BALANCE INT NOT NULL
    , PRIMARY KEY (ID)
    , CONSTRAINT FK_MERCHANT_BALANCE_1 FOREIGN KEY (PARTNER) REFERENCES PARTNER(ID)  ON DELETE CASCADE
    , CONSTRAINT FK_MERCHANT_BALANCE_2 FOREIGN KEY (BALANCE) REFERENCES BALANCE(ID) ON DELETE CASCADE
);

CREATE TABLE MERCHANT_STATE(
    ID VARCHAR(20) NOT NULL
  , DESCRIPTION VARCHAR(512)
  , PRIMARY KEY (ID)
);

INSERT INTO MERCHANT_STATE (ID) VALUES ('ACTIVE');
INSERT INTO MERCHANT_STATE (ID) VALUES ('DISABLED');

CREATE TABLE MERCHANT (
    ID INT NOT NULL  AUTO_INCREMENT
    , PARTNER INT NOT NULL
    , STATE_ID VARCHAR(20) NOT NULL
    , NAME VARCHAR(50) NOT NULL
    , FULL_NAME VARCHAR(120) NOT NULL
    , PRIMARY KEY (ID)
    , CONSTRAINT FK_MERCHANT_1 FOREIGN KEY (STATE_ID) REFERENCES MERCHANT_STATE(ID) ON DELETE SET NULL
    , CONSTRAINT FK_MERCHANT_2 FOREIGN KEY (PARTNER) REFERENCES PARTNER(ID)  ON DELETE CASCADE
);


CREATE TABLE TXN (
    ID INT NOT NULL  AUTO_INCREMENT
    , SOURCE_BALANCE INT NOT NULL
    , DESTINATION_BALANCE INT NOT NULL
    , AMOUNT_VALUE NUMBER NOT NULL
    , AMOUNT_CURRENCY VARCHAR(5) NOT NULL
    , PRIMARY KEY (ID)
    , CONSTRAINT FK_TXN_1 FOREIGN KEY (SOURCE_BALANCE) REFERENCES BALANCE(ID)  ON DELETE CASCADE
    , CONSTRAINT FK_TXN_2 FOREIGN KEY (DESTINATION_BALANCE) REFERENCES BALANCE(ID) ON DELETE CASCADE
);

CREATE TABLE PAYMENT (
	ID INT NOT NULL  AUTO_INCREMENT
	, MERCHANT INT NOT NULL
  , TRANSACTION INT
  , AMOUNT_VALUE NUMBER NOT NULL
  , AMOUNT_CURRENCY VARCHAR(5) NOT NULL
	, PRIMARY KEY (ID)
  , CONSTRAINT FK_PAYMENT_1 FOREIGN KEY (MERCHANT) REFERENCES MERCHANT(ID) ON DELETE CASCADE
  , CONSTRAINT FK_PAYMENT_2 FOREIGN KEY (TRANSACTION) REFERENCES TXN(ID) ON DELETE SET NULL
);
