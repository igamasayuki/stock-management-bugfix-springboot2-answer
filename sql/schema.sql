--テーブルがあった場合は事前に削除
DROP TABLE members;
DROP TABLE books;

-- メンバー情報テーブル
create table members(
  id		serial	primary key,
  name		text	not null,
  mail_address	text	not null unique,
  password	text	not null
);

-- 書籍情報テーブル
create table books (
  id integer primary key,
  name text not null,
  author text not null,
  publisher text not null,
  price integer not null,
  isbncode text not null,
  saledate date not null,
  explanation text not null,
  image text not null,
  stock integer not null default 0
);