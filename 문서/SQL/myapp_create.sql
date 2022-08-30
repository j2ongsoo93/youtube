create table	member	(				
mem_id	varchar2(200)	primary key				,
mem_name	varchar2(200)					,
password	varchar2(200)					,
auth	varchar2(200)					,
regist_date	date				default sysdate	,
update_date	date				default sysdate	,
regist_id	varchar2(200)					,
update_id	varchar2(200)					,
regist_ip	varchar2(200)					,
update_ip	varchar2(200)					,
mem_profile	varchar2(200)					
						
						
						
);

create table	board_youtube	(				
ytb_idx	number	primary key				,
ytb_title	varchar2(200)					,
ytb_url	varchar2(200)			not null		,
ytb_info	varchar2(2000)					,
ytb_regdate	date				default sysdate	,
ytb_hit	number				default 0	,
ytb_like	number				default 0	,
ytb_channel_name	varchar2(200)					,
ytb_subscribes	number					,
ytb_channel_url	varchar2(200)					,
ytb_thumbnail	varchar2(200)											
);						

create sequence board_youtube_seq start with 10001;