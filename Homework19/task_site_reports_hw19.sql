--  Задание:
--
--  1. Написать запрос, считающий суммарное количество имеющихся на сайте новостей и обзоров.
--  В результате выполнения запроса должно получиться:
--  SUM
--  7

-- with UNION

SELECT COUNT(*) AS SUM FROM (SELECT n.n_header AS header FROM news n UNION SELECT r.r_header AS header FROM reviews r) AS news_reviews;

-- without UNION

SELECT COUNT(DISTINCT(COALESCE(n.n_header, r.r_header))) AS SUM FROM 
(
  SELECT TABLE_NAME <> 'news' tn
    FROM INFORMATION_SCHEMA.TABLES
   WHERE TABLE_SCHEMA = SCHEMA()
     AND TABLE_NAME IN('news', 'reviews')
) t LEFT JOIN news n
    ON t.tn = 0 LEFT JOIN reviews r
    ON t.tn = 1;

--  2. Написать запрос, показывающий список категорий новостей и количество новостей в каждой категории.
--   
--  В результате выполнения запроса должно получиться:
--   
--  nc_name
--  COUNT(`n_id`)
--  Финансы
--  1
--  Законодательство
--  0
--  Логистика
--  4
--  Строительство
--  0

-- with JOIN

SELECT nc.nc_name, COUNT(n.n_id) FROM news_categories nc LEFT JOIN news n ON nc.nc_id = n.n_category GROUP BY nc.nc_name;

-- with subquery

SELECT nc.nc_name, (SELECT COUNT(n.n_id) FROM news n WHERE nc.nc_id = n.n_category) FROM news_categories nc;

--  3. Написать запрос, показывающий список категорий обзоров и количество обзоров в каждой категории.
--  
--  В результате выполнения запроса должно получиться:
--  
--  rc_name
--  COUNT(`r_id`)
--  Технологии
--  2
--  Товары и услуги
--  0

-- with JOIN

SELECT rc.rc_name, COUNT(r_id) FROM reviews_categories rc LEFT JOIN reviews r ON rc.rc_id = r.r_category GROUP BY rc.rc_name;

-- with subquery

SELECT rc.rc_name, (SELECT COUNT(r.r_id) FROM reviews r WHERE rc.rc_id = r.r_category) FROM reviews_categories rc;

--  4. Написать запрос, показывающий список категорий новостей, категорий обзоров и дату самой свежей публикации в каждой категории.
--  
--  В результате выполнения запроса должно получиться:
--  
--  category_name
--  last_date
--  Технологии
--  2012-12-12 06:31:13
--  Финансы
--  2012-12-03 04:15:27
--  Логистика
--  2012-12-20 06:11:42

SELECT nc.nc_name, MAX(n.n_dt) FROM news_categories nc JOIN news n ON nc.nc_id = n.n_category GROUP BY nc.nc_name
UNION ALL
SELECT rc.rc_name, MAX(r.r_dt) FROM reviews_categories rc JOIN reviews r ON rc.rc_id = r.r_category GROUP BY rc.rc_name;

--  5. Написать запрос, показывающий список страниц сайта верхнего уровня (у таких страниц нет родительской страницы) и список баннеров для каждой такой страницы.
--  
--  В результате выполнения запроса должно получиться:
--  
--  p_name
--  b_id
--  b_url
--  Юридическим лицам
--  1
--  http://tut.by
--  Юридическим лицам
--  2
--  http://tut.by
--  Юридическим лицам
--  7
--  http://habrahabr.ru
--  Физическим лицам
-- 1
--  http://tut.by

SELECT p.p_name, b.b_id, b.b_url FROM pages p, banners b, m2m_banners_pages m2mbp WHERE p.p_parent IS NULL AND m2mbp.p_id = p.p_id AND m2mbp.b_id = b.b_id;

--  6. Написать запрос, показывающий список страниц сайта, на которых есть баннеры.
--  
--  В результате выполнения запроса должно получиться:
--
--  p_name
--  Юридическим лицам
--  Физическим лицам
--  Образцы договоров
--  Банковские реквизиты
--  Схема проезда к офису

SELECT DISTINCT(p.p_name) FROM pages p, m2m_banners_pages m2mbp WHERE p.p_id = m2mbp.p_id;

--  7. Написать запрос, показывающий список страниц сайта, на которых нет баннеров.
--  
--  В результате выполнения запроса должно получиться:
--  
--  p_name
--  Почта и телефон
--  Договоры оптовых закупок

SELECT p.p_name FROM pages p WHERE p.p_id NOT IN(SELECT m2mbp.p_id FROM m2m_banners_pages m2mbp);

--  8. Написать запрос, показывающий список баннеров, размещённых хотя бы на одной странице сайта.
--  
--  В результате выполнения запроса должно получиться:
--  
--  b_id
--  b_url
--  1
--  http://tut.by
--  2
--  http://tut.by
--  7
--  http://habrahabr.ru
--  4
--  http://onliner.by
--  3
--  http://onliner.by

SELECT DISTINCT(b.b_id), b.b_url FROM banners b, m2m_banners_pages m2mbp WHERE m2mbp.b_id = b.b_id;

--  9. Написать запрос, показывающий список баннеров, не размещённых ни на одной странице сайта.
--  
--  В результате выполнения запроса должно получиться:
--
--  b_id
--  b_url
--  5
--  http://google.by
--  6
--  http://google.com
--  8
--  http://habrahabr.ru
--  9
--  http://gismeteo.by
--  10
--  http://gismeteo.ru

-- with JOIN

SELECT DISTINCT(b.b_id), b.b_url FROM banners b LEFT JOIN m2m_banners_pages m2mbp ON b.b_id = m2mbp.b_id WHERE m2mbp.b_id IS NULL AND b.b_url IS NOT NULL;

-- with subquery

SELECT DISTINCT(b.b_id), b.b_url FROM banners b WHERE b.b_id NOT IN(SELECT m2mbp.b_id FROM m2m_banners_pages m2mbp) AND b.b_url IS NOT NULL;

--  10. Написать запрос, показывающий баннеры, для которых отношение кликов к показам >= 80% (при условии, что баннер был показан хотя бы один раз).
--  
--  В результате выполнения запроса должно получиться:

--  b_id
--  b_url
--  rate
--  2
--  http://tut.by
--  150.0000
--  Да-да, в исходных данных ошибка. Потому тут – 150%.
--  3
--  http://onliner.by
--  90.0000
--  5
--  http://google.by
--  100.0000
--  6
--  http://google.com
--  100.0000
--  7
--  http://habrahabr.ru
--  99.7998
--  8
--  http://habrahabr.ru
--  98.0000

-- with WHERE

SELECT b.b_id, b.b_url, ((b.b_click / b.b_show) * 100) AS rate FROM banners b WHERE ((b.b_click / b.b_show) * 100) > 80;

-- with HAVING

SELECT b.b_id, b.b_url, ((b.b_click / b.b_show) * 100) AS rate FROM banners b HAVING rate > 80;

--  11. Написать запрос, показывающий список страниц сайта, на которых показаны баннеры с текстом (в поле `b_text` не NULL).
--
--  В результате выполнения запроса должно получиться:
--  
--  p_name
--  Юридическим лицам
--  Физическим лицам
--  Банковские реквизиты
--  Схема проезда к офису

SELECT DISTINCT(p.p_name) FROM pages p, banners b, m2m_banners_pages m2mbp WHERE p.p_id = m2mbp.p_id AND b.b_id = m2mbp.b_id AND b.b_text IS NOT NULL;

--  12. Написать запрос, показывающий список страниц сайта, на которых показаны баннеры с картинкой (в поле `b_pic` не NULL).
--  
--  В результате выполнения запроса должно получиться:
--  
--  p_name
--  Юридическим лицам
--  Образцы договоров
--  Банковские реквизиты
--  Схема проезда к офису

SELECT DISTINCT(p.p_name) FROM pages p, banners b JOIN m2m_banners_pages m2mbp WHERE m2mbp.p_id = p.p_id AND m2mbp.b_id = b.b_id AND b.b_pic IS NOT NULL;

--  13. Написать запрос, показывающий список публикаций (новостей и обзоров) за 2011-й год.
--  
--  В результате выполнения запроса должно получиться:
--  
--  header
--  date
--  Контрабанда железобетонных плит
--  2011-09-14 06:19:08
--  Почта России: вчера, сегодня и снова вчера
--  2011-08-17 09:06:30
--  Роботы на страже строек
--  2011-10-03 05:17:37

SELECT n.n_header AS header, n.n_dt AS date FROM news n WHERE n.n_dt BETWEEN '2011-01-01' AND '2012-01-01'
UNION
SELECT r.r_header, r.r_dt FROM reviews r WHERE r.r_dt between '2011-01-01' AND '2012-01-01';

--  14. Написать запрос, показывающий список категорий публикаций (новостей и обзоров), в которых нет публикаций.
--  
--  В результате выполнения запроса должно получиться:
--  
--  category
--  Законодательство
--  Строительство
--  Товары и услуги

SELECT DISTINCT(nc.nc_name) FROM news n RIGHT JOIN news_categories nc ON nc.nc_id = n.n_category WHERE n.n_category IS NULL
UNION
SELECT DISTINCT(rc.rc_name) FROM reviews r RIGHT JOIN reviews_categories rc ON rc.rc_id = r.r_category WHERE r.r_category IS NULL;

--  15. Написать запрос, показывающий список новостей из категории «Логистика» за 2012-й год.
--  
--  В результате выполнения запроса должно получиться:
--  
--  n_header
--  n_dt
--  Самолётом или поездом?
--  2012-12-20 06:11:42
--  Куда всё катится?
--  2012-12-11 04:36:17

SET @cat_name = "Логистика";
SELECT n.n_header, n.n_dt FROM news n
WHERE n.n_category = (SELECT nc.nc_id FROM news_categories nc WHERE nc.nc_name = @cat_name)
AND n.n_dt BETWEEN '2012-01-01' AND '2013-01-01';

--  16. Написать запрос, показывающий список годов, за которые есть новости, а также количество новостей за каждый из годов.
--  
--  В результате выполнения запроса должно получиться:
--  
--  year
--  COUNT(*)
--  2011
--  2
--  2012
--  3

SELECT YEAR(n.n_dt) AS year, COUNT(*) FROM news n GROUP BY year;

--  17. Написать запрос, показывающий URL и id таких баннеров, где для одного и того же URL есть несколько баннеров.
--  
--  В результате выполнения запроса должно получиться:
--  
--  b_url
--  b_id
--  http://tut.by
--  1
--  http://tut.by
--  2
--  http://onliner.by
--  3
--  http://onliner.by
--  4
--  http://habrahabr.ru
--  7
--  http://habrahabr.ru
--  8

SELECT b.b_url, b.b_id FROM banners b WHERE b.b_url IN (SELECT b.b_url FROM banners b GROUP BY b.b_url HAVING COUNT(*) > 1);

--  18. Написать запрос, показывающий список непосредственных подстраниц страницы «Юридическим лицам» со списком баннеров этих подстраниц.
--  
--  В результате выполнения запроса должно получиться:
--  
--  p_name
--  b_id
--  b_url
--  Образцы договоров
--  4
--  http://onliner.by
--  Банковские реквизиты
--  1
--  http://tut.by
--  Банковские реквизиты
--  2
--  http://tut.by

SET @parent_name = "Юридическим лицам";
SELECT p.p_name, m2mbp.b_id, b.b_url FROM pages p
JOIN m2m_banners_pages m2mbp ON p.p_id = m2mbp.p_id
JOIN banners b ON b.b_id = m2mbp.b_id
WHERE p.p_parent = (SELECT p2.p_id FROM pages p2 WHERE p2.p_name = @parent_name);

--  19. Написать запрос, показывающий список всех баннеров с картинками (поле `b_pic` не NULL), отсортированный по убыванию отношения кликов по баннеру к показам баннера.
--  
--  В результате выполнения запроса должно получиться:
--  
--  b_id
--  b_url
--  rate
--  2
--  http://tut.by
--  1.5000
--  Да-да, в исходных данных ошибка. Потому тут – 150%.
--  5
--  http://google.by
--  1.0000
--  6
--  http://google.com
--  1.0000
--  7
--  http://habrahabr.ru
--  0.9980
--  4
--  http://onliner.by
--  0.0200

SELECT b.b_id, b.b_url, (b.b_click / b.b_show) AS rate FROM banners b WHERE b.b_pic IS NOT NULL ORDER BY rate DESC;

--  20. Написать запрос, показывающий самую старую публикацию на сайте (не важно – новость это или обзор).
--  
--  В результате выполнения запроса должно получиться:
--  
--  header
--  date
--  Почта России: вчера, сегодня и снова вчера
--  2011-08-17 09:06:30

SELECT header, date
FROM (
  SELECT n.n_header AS header, n.n_dt AS date FROM news n
    UNION
  SELECT r.r_header AS header, r.r_dt AS date FROM reviews r
) AS publications
ORDER BY date ASC LIMIT 1;

--  21. Написать запрос, показывающий список баннеров, URL которых встречается в таблице один раз.
--  
--  В результате выполнения запроса должно получиться:
--  
--  b_url
--  b_id
--  http://google.by
--  5
--  http://google.com
--  6
--  http://gismeteo.by
--  9
--  http://gismeteo.ru
--  10

SELECT b.b_url, b.b_id FROM banners b WHERE b.b_url IN (SELECT b.b_url FROM banners b GROUP BY b.b_url HAVING COUNT(b.b_url) = 1);

--  22. Написать запрос, показывающий список страниц сайта в порядке убывания количества баннеров, расположенных на странице. Для случаев, когда на нескольких страницах расположено одинаковое количество баннеров, этот список страниц должен быть отсортирован по возрастанию имён страниц.
--  
--  В результате выполнения запроса должно получиться:
--
--  p_name
--  banners_count
--  Схема проезда к офису
--  3
--  Юридическим лицам
--  3
--  Банковские реквизиты
--  2
--  Образцы договоров
--  1
--  Физическим лицам
--  1

SELECT p_name, COUNT(m2mbp.b_id) AS banners_count FROM pages p JOIN m2m_banners_pages m2mbp ON p.p_id = m2mbp.p_id GROUP BY p_name ORDER BY banners_count DESC;

--  23. Написать запрос, показывающий самую «свежую» новость и самый «свежий» обзор.
--  
--  В результате выполнения запроса должно получиться:
--
--  header
--  date
--  Самолётом или поездом?
--  2012-12-20 06:11:42
--  Когда всё это кончится?!
--  2012-12-12 06:31:13

SELECT header, date FROM
((SELECT n.n_header AS header, n.n_dt AS date FROM news n ORDER BY date DESC LIMIT 1)
UNION
(SELECT r.r_header AS header, r.r_dt AS date FROM reviews r ORDER BY date DESC LIMIT 1)) AS news_reviews;

--  24. Написать запрос, показывающий баннеры, в тексте которых встречается часть URL, на который ссылается баннер.
--  
--  В результате выполнения запроса должно получиться:
--  
--  b_id
--  b_url
--  b_text
--  1
--  http://tut.by
--  TUT.BY
--  3
--  http://onliner.by
--  ONLINER.BY
--  5
--  http://google.by
--  GOOGLE.BY
--  8
--  http://habrahabr.ru
--  HABRAHABR.RU

SET @url_pos = CHAR_LENGTH("http://") + 1;
SELECT b.b_id, b.b_url, b.b_text FROM banners b WHERE b.b_text LIKE (CONCAT("%", SUBSTRING(b.b_url, @url_pos), "%"));

--  25. Написать запрос, показывающий страницу, на которой размещён баннер с самым высоким отношением кликов к показам.
--  
--  В результате выполнения запроса должно получиться:
--  
--  p_name
--  Юридическим лицам

-- with ORDER

SELECT p.p_name FROM banners b
LEFT JOIN m2m_banners_pages m2mbp ON m2mbp.b_id = b.b_id
LEFT JOIN pages p ON m2mbp.p_id = p.p_id
ORDER BY (b.b_click / b.b_show) DESC LIMIT 1;

-- with MAX

SELECT p.p_name FROM pages p
LEFT JOIN m2m_banners_pages m2mbp ON m2mbp.p_id = p.p_id
LEFT JOIN banners b ON b.b_id = m2mbp.b_id
WHERE (b.b_click / b.b_show) = (SELECT MAX(b2.b_click / b2.b_show) FROM banners b2) LIMIT 1;

--  26. Написать запрос, считающий среднее отношение кликов к показам по всем баннерам, которые были показаны хотя бы один раз.
--  
--  В результате выполнения запроса должно получиться:
--  
--  AVG(`b_click`/`b_show`)
--  0.81224975

SELECT AVG(b.b_click / b.b_show) FROM banners b WHERE b.b_show > 0;

--  27. Написать запрос, считающий среднее отношение кликов к показам по баннерам, у которых нет графической части (поле `b_pic` равно NULL).
--  
--  В результате выполнения запроса должно получиться:
--  
--  AVG(`b_click`/`b_show`)
--  0.66000000

SELECT AVG(b.b_click / b.b_show) FROM banners b WHERE b.b_pic IS NULL;

--  28. Написать запрос, показывающий количество баннеров, размещённых на страницах сайта верхнего уровня (у таких страниц нет родительских страниц).
--  
--  В результате выполнения запроса должно получиться:
--  COUNT
--  4

SELECT COUNT(m2mbp.b_id) AS COUNT FROM m2m_banners_pages m2mbp JOIN pages p ON m2mbp.p_id = p.p_id WHERE p.p_parent IS NULL;

--  29. Написать запрос, показывающий баннер(ы), который(ые) показаны на самом большом количестве страниц.
--
--  В результате выполнения запроса должно получиться:
--  
--  b_id
--  b_url
--  COUNT
--  1
--  http://tut.by
--  4

SELECT m2mbp.b_id, b.b_url, COUNT(m2mbp.p_id) AS COUNT FROM m2m_banners_pages m2mbp
JOIN banners b ON m2mbp.b_id = b.b_id
GROUP BY m2mbp.b_id ORDER BY COUNT DESC LIMIT 1;

--  30. Написать запрос, показывающий страницу(ы), на которой(ых) показано больше всего баннеров.
--  
--  В результате выполнения запроса должно получиться:
--  
--  p_name
--  COUNT
--  Юридическим лицам
--  3
-- Схема проезда к офису
--  3

SELECT p.p_name, COUNT(m2mbp.b_id) AS COUNT FROM m2m_banners_pages m2mbp
JOIN pages p ON m2mbp.p_id = p.p_id GROUP BY p.p_name
HAVING COUNT = (SELECT COUNT(m2mbp2.b_id) AS b2_id FROM m2m_banners_pages m2mbp2 GROUP BY m2mbp2.p_id ORDER BY COUNT(m2mbp2.b_id) DESC LIMIT 1);
