drop table if exists tbl_point_details;

create table tbl_point_details
(
    id                     bigint unsigned auto_increment primary key comment '포인트 내역 ID',
    individual_member_id   bigint unsigned not null comment '개인회원 ID',
    point_type             enum ('earn', 'use', 'expire', 'purchase_cancel') not null comment '포인트 유형',
    point_amount           int not null comment '포인트 금액',
    remaining_point_amount int not null default 0 comment '잔여 포인트 금액',
    payment_amount         int not null default 0 comment '결제 금액',
    expire_datetime        datetime null comment '유효기간',
    payment_order_id       varchar(120) null comment '결제 주문번호',
    payment_receipt_id     varchar(120) null comment '결제 영수증 번호',
    cancelled_datetime     datetime null comment '구매 취소 시각',
    created_datetime       datetime default current_timestamp,
    updated_datetime       datetime default current_timestamp,
    constraint fk_point_details_member foreign key (individual_member_id) references tbl_individual_member (id)
);
