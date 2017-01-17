   create table answer (
        seq bigint generated by default as identity,
        contents varchar(255),
        create_date binary(255),
        delete_date binary(255),
        delete_flag integer,
        delete_user_id bigint,
        question_seq bigint,
        writer_id bigint,
        primary key (seq)
    )
    create table question (
        seq bigint generated by default as identity,
        contents varchar(255),
        create_date binary(255),
        delete_date binary(255),
        delete_flag integer,
        title varchar(255),
        delete_user_id bigint,
        writer_id bigint,
        primary key (seq)
    )
    create table user (
        id bigint generated by default as identity,
        email varchar(255),
        name varchar(255) not null,
        password varchar(255) not null,
        uid varchar(20) not null,
        primary key (id)
    )
    alter table user 
        add constraint UK_a7hlm8sj8kmijx6ucp7wfyt31 unique (uid)
    alter table answer 
        add constraint fk_answer_delete_user 
        foreign key (delete_user_id) 
        references user
    alter table answer 
        add constraint fk_answer_to_question 
        foreign key (question_seq) 
        references question
    alter table answer 
        add constraint fk_answer_writer 
        foreign key (writer_id) 
        references user
    alter table question 
        add constraint fk_question_delete_user 
        foreign key (delete_user_id) 
        references user
    alter table question 
        add constraint fk_question_writer 
        foreign key (writer_id) 
        references user
    alter table question 
    	add column show_count integer default 0 not null