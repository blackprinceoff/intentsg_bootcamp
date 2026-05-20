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
WHERE observation_date >= DATE_SUB(CURDATE(), INTERVAL 3 DAY);

SELECT observation_date, MAX(temperature) AS max_temperature
FROM WeatherObservation
GROUP BY observation_date
HAVING MAX(temperature) > 30;






