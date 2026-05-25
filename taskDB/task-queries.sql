CREATE DATABASE IF NOT EXISTS WeatherObservation;
USE WeatherObservation;

CREATE TABLE IF NOT EXISTS WeatherObservation(
    observation_id INT AUTO_INCREMENT PRIMARY KEY,
    station_id VARCHAR(50) NOT NULL,
    observation_date DATE NOT NULL,
    observation_time TIME NOT NULL,
    temperature DECIMAL(5,2) NOT NULL,
    humidity DECIMAL(5,2) NOT NULL,
    wind_speed DECIMAL(5,2) NOT NULL,
    wind_direction VARCHAR(20) NOT NULL,
    precipitation DECIMAL(5,2) NOT NULL,
    description TEXT
);

INSERT INTO WeatherObservation (station_id, observation_date, observation_time, temperature, humidity, wind_speed, wind_direction, precipitation, description) 
VALUES 
('station1', '2026-01-01', '10:00:00', 24.5, 60, 10, 'N', 0, 'Clear sky'),
('station1', '2026-01-01', '12:00:00', 31.0, 65, 15, 'E', 0, 'Partly cloudy'),
('station1', '2026-01-01', '18:00:00', 28.0, 70, 20, 'S', 5, 'Rainy'),
('station2', '2026-01-02', '12:00:00', 24.5, 75, 25, 'W', 10, 'Stormy'),
('station3', '2026-01-03', '14:00:00', 32.0, 60, 20, 'NE', 0, 'Clear sky');

SELECT AVG(temperature)
AS avg_temp
FROM WeatherObservation
WHERE observation_date='2026-01-01';

SELECT MAX(humidity)
AS max_humidity, MIN(humidity)
AS min_humidity
FROM WeatherObservation
WHERE observation_date='2026-01-01';

SELECT SUM(precipitation) AS total_precipitation
FROM WeatherObservation
WHERE observation_date BETWEEN '2026-01-01' AND '2026-01-03';

SELECT observation_date, MAX(temperature) AS max_temperature
FROM WeatherObservation
GROUP BY observation_date
HAVING MAX(temperature) > 30;


--PART 2

CREATE DATABASE IF NOT EXISTS CompanyHR;
USE CompanyHR;

CREATE TABLE IF NOT EXISTS unit_registry (
    unit_code VARCHAR(20) PRIMARY KEY,
    display_title VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS staff_records (
    sys_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    given_name VARCHAR(50) NOT NULL,
    family_name VARCHAR(50) NOT NULL,
    emp_sex CHAR(1) NOT NULL,
    monthly_rate NUMERIC(10,2),
    hire_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS assignment_history (
    record_no BIGINT PRIMARY KEY AUTO_INCREMENT,
    staff_sys_id BIGINT NOT NULL,
    unit_code VARCHAR(20) NOT NULL,
    role_title VARCHAR(100) NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (staff_sys_id) REFERENCES staff_records(sys_id),
    FOREIGN KEY (unit_code) REFERENCES unit_registry(unit_code)
);


INSERT IGNORE INTO unit_registry (unit_code, display_title) VALUES
    ('DEV', 'Development'),
    ('HR', 'Human Resources'),
    ('FIN', 'Finance'),
    ('MKT', 'Marketing');

INSERT IGNORE INTO staff_records (sys_id, given_name, family_name, emp_sex, monthly_rate, hire_date) VALUES
    (1, 'Олексій', 'Коваленко', 'M', 45000.00, '2018-03-15'),
    (2, 'Наталія', 'Бойко', 'F', 38000.00, '2019-07-01'),
    (3, 'Ігор', 'Мельник', 'M', 62000.00, '2020-01-10'),
    (4, 'Оксана', 'Лисенко', 'F', 41000.00, '2017-11-20'),
    (5, 'Дмитро', 'Ткаченко', 'M', 75000.00, '2021-05-05'),
    (6, 'Ірина', 'Савченко', 'F', 55000.00, '2016-08-30'),
    (7, 'Андрій', 'Гриценко', 'M', 90000.00, '2022-02-14'),
    (8, 'Людмила', 'Петренко', 'F', 48000.00, '2015-06-01'),
    (9, 'Василь', 'Кравченко', 'M', 40000.00, '2023-09-01'),
    (10, 'Тетяна', 'Іванченко', 'F', 70000.00, '2019-04-15'),
    (11, 'Роман', 'Шевченко', 'M', 60000.00, '2020-10-01'),
    (12, 'Юлія', 'Харченко', 'F', 85000.00, '2018-12-01'),
    (13, 'Сергій', 'Пономаренко', 'M', 80000.00, '2014-03-20'),
    (14, 'Вікторія', 'Зінченко', 'F', 50000.00, '2022-07-11'),
    (15, 'Михайло', 'Руденко', 'M', 35000.00, '2013-01-15');

INSERT IGNORE INTO assignment_history
    (record_no, staff_sys_id, unit_code, role_title, is_active) VALUES
    (1, 1, 'DEV', 'Developer', TRUE),
    (2, 3, 'DEV', 'Senior Developer', TRUE),
    (3, 5, 'DEV', 'Tech Lead', TRUE),
    (4, 7, 'DEV', 'Manager', TRUE),  
    (5, 9, 'DEV', 'Developer', TRUE),
    (6, 11, 'DEV', 'Senior Developer', TRUE),
    (7, 2, 'HR',  'HR Specialist', TRUE),
    (8, 4, 'HR',  'HR Specialist', TRUE),
    (9, 10, 'HR',  'Manager', TRUE),  
    (10, 6, 'FIN', 'Financial Analyst', TRUE),
    (11, 12, 'FIN', 'Manager', TRUE),  
    (12, 8, 'MKT', 'Marketing Specialist', TRUE),
    (13, 13, 'MKT', 'Manager', TRUE), 
    (14, 14, 'MKT', 'Marketing Specialist', TRUE),
    (15, 15, 'DEV', 'Developer', FALSE);

-- 1.1
 
SELECT emp_sex AS gender, COUNT(*) AS total_employees
FROM staff_records
GROUP BY emp_sex;

-- 1.2

SELECT sr.given_name, sr.family_name
FROM assignment_history ah
JOIN staff_records sr ON sr.sys_id = ah.staff_sys_id
WHERE ah.unit_code = 'DEV'
  AND ah.role_title = 'Manager'
  AND ah.is_active = TRUE;

-- 1.3 

SELECT sr.family_name
FROM assignment_history ah
JOIN staff_records sr ON sr.sys_id = ah.staff_sys_id
WHERE ah.unit_code = 'HR'
  AND ah.is_active = TRUE;

-- 1.4 

SELECT ah.unit_code, COUNT(*) AS active_employees
FROM assignment_history ah
WHERE ah.is_active = TRUE
GROUP BY ah.unit_code
ORDER BY active_employees DESC;

-- 1.5

SELECT ur.display_title AS department_name, sr.given_name, sr.family_name
FROM assignment_history ah
JOIN unit_registry ur ON ur.unit_code = ah.unit_code
JOIN staff_records sr ON sr.sys_id = ah.staff_sys_id
WHERE ah.role_title = 'Manager'
  AND ah.is_active = TRUE;

-- 1.6

SELECT ah.unit_code, ROUND(AVG(sr.monthly_rate), 2) AS avg_monthly_rate
FROM assignment_history ah
JOIN staff_records sr ON sr.sys_id = ah.staff_sys_id
WHERE ah.is_active = TRUE
GROUP BY ah.unit_code
ORDER BY avg_monthly_rate DESC;


-- 2.1 

SELECT ah.unit_code, ur.display_title, ROUND(AVG(DATEDIFF(CURDATE(), sr.hire_date) / 365.25), 2) AS avg_experience_years
FROM assignment_history ah
JOIN staff_records sr ON sr.sys_id = ah.staff_sys_id
JOIN unit_registry ur ON ur.unit_code = ah.unit_code
WHERE ah.is_active = TRUE
GROUP BY ah.unit_code, ur.display_title
ORDER BY avg_experience_years DESC;

-- 2.2

SELECT sr.given_name, sr.family_name, sr.monthly_rate, ah.unit_code
FROM assignment_history ah
JOIN staff_records sr ON sr.sys_id = ah.staff_sys_id
WHERE ah.is_active = TRUE
  AND sr.monthly_rate = (
      SELECT MAX(sr2.monthly_rate)
      FROM assignment_history ah2
      JOIN staff_records sr2 ON sr2.sys_id = ah2.staff_sys_id
      WHERE ah2.unit_code = ah.unit_code
      AND ah2.is_active = TRUE
      )
ORDER BY ah.unit_code;

-- 2.3

SELECT ah.role_title, COUNT(*) AS headcount
FROM assignment_history ah
WHERE ah.unit_code = 'DEV'
  AND ah.is_active = TRUE
GROUP BY ah.role_title
ORDER BY headcount DESC;
