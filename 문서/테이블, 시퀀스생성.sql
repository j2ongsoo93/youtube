create table USER_INFO
(
    USER_IDX      NUMBER        not null
        primary key,
    USER_ID       VARCHAR2(200) not null,
    USER_PWD      VARCHAR2(200) not null,
    USER_NAME     VARCHAR2(200) not null,
    USER_PHONE    VARCHAR2(200) not null,
    USER_EMAIL    VARCHAR2(200) not null,
    COMPANY_IDX   NUMBER        not null,
    DEPT_ID       NUMBER        not null,
    USER_REGDATE  DATE          default sysdate,
    USER_ROLE     VARCHAR2(200) default 'user',
    USER_JOB      VARCHAR2(200) default null,
    USER_POSITION VARCHAR2(200) default null
)
/

create table BOARD_FREE
(
    BOARD_IDX     NUMBER         not null
        primary key,
    USER_ID       VARCHAR2(200)  not null,
    USER_NAME     VARCHAR2(200)  not null,
    BOARD_CONTENT VARCHAR2(4000) not null
)
/

create table MEMBER
(
    MEM_ID      VARCHAR2(200) not null
        primary key,
    MEM_NAME    VARCHAR2(200),
    PASSWORD    VARCHAR2(200),
    REGIST_DATE DATE default (null),
    UPDATE_DATE DATE default (null),
    REGIST_ID   VARCHAR2(200),
    UPDATE_ID   VARCHAR2(200),
    REGIST_IP   VARCHAR2(200),
    UPDATE_IP   VARCHAR2(200),
    MEM_PROFILE VARCHAR2(200),
    MEM_EMAIL   VARCHAR2(200),
    ENABLED     CHAR
        check (ENABLED = '0' or ENABLED = '1')
)
/

create table BOARD_YOUTUBE
(
    YTB_IDX           NUMBER        not null
        primary key,
    YTB_TITLE         VARCHAR2(4000),
    YTB_URL           VARCHAR2(400) not null,
    YTB_INFO          LONG,
    YTB_REGDATE       DATE   default sysdate,
    YTB_HIT           NUMBER default 0,
    YTB_LIKE          NUMBER default 0,
    YTB_CHANNEL_NAME  VARCHAR2(200),
    YTB_CHANNEL_URL   VARCHAR2(200),
    YTB_THUMBNAIL     VARCHAR2(200),
    MEM_ID            VARCHAR2(200),
    YTB_CHANNEL_THUMB VARCHAR2(200),
    YTB_EMBED_URL     VARCHAR2(200)
)
/

create table MEMBER_AUTH
(
    MEM_ID    VARCHAR2(200)                not null
        primary key
        references MEMBER,
    AUTHORITY VARCHAR2(200) default 'USER' not null
)
/

create table YTB_SUBSCRIBES
(
    SUB_IDX          NUMBER not null
        primary key,
    MEM_ID           VARCHAR2(200)
        references MEMBER,
    YTB_CHANNEL_NAME VARCHAR2(200)
)
/

create table MEMBER_PROFILE_IMG
(
    IMG_IDX       NUMBER not null
        primary key,
    IMG_FILE_NAME VARCHAR2(200),
    MEM_ID        VARCHAR2(200)
        references MEMBER
)
/

create table AUTH
(
    MEM_ID VARCHAR2(200)                not null
        primary key
        references MEMBER,
    AUTH   VARCHAR2(200) default 'USER' not null
)
/

create table YTB_REPLY
(
    RE_IDX          NUMBER          not null primary key,
    MEM_ID          VARCHAR2(200)   references MEMBER,
    RE_CONTENT      VARCHAR2(4000)  not null,
    RE_REG_DATE     DATE            not null,
    RE_UPDATE_DATE  DATE,
    YTB_IDX         NUMBER
        references BOARD_YOUTUBE
)
/

create table YTB_REPLY_REPLY
(
    RERE_IDX        NUMBER         not null
        primary key,
    RE_IDX          NUMBER
        references YTB_REPLY,
    MEM_ID          VARCHAR2(200)
        references MEMBER,
    RERE_CONTENT    VARCHAR2(4000) not null,
    RERE_REG_DATE   DATE           not null,
    RERE_UPDATE_DATE  DATE,
    RERE_ANNOTATION VARCHAR2(200)
)
/

create sequence BOARD_FREE_SEQ start with 10000
/

create sequence MEMBER_SEQ start with 10000
/

create sequence BOARD_YOUTUBE_SEQ start with 10000
/

create sequence YTB_SUBSCRIBES_SEQ start with 10000
/

create sequence IMG_IDX_SEQ start with 10000
/

create sequence YTB_REPLY_SEQ start with 10000
/

create sequence YTB_REPLY_REPLY_SEQ start with 10000
/