select count(*) from books;
select distinct count(*) from books;



select * from book_borrow
where is_returned=0;


select name from book_categories;

# name,author ,isbn,desc,year

select name,isbn,year,author,description from books
where name = 'Clean Code';

select full_name from users
where email = 'librarian55@library';

select status from users where email='anisa.stokes@gmail.com';

select bc.name, count(*) from book_borrow bb
                                 inner join books b on bb.book_id = b.id
                                 inner join book_categories bc on b.book_category_id=bc.id
group by name
order by 2 desc;


select id,name,author from books
where name = 'Head Second Java'
order by id desc;