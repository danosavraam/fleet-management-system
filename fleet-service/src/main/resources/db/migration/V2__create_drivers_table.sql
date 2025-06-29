CREATE TABLE drivers (
  personal_id BIGINT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  phone VARCHAR(100) NOT NULL,
  car_id VARCHAR(255),
  CONSTRAINT fk_driver_car FOREIGN KEY (car_id) REFERENCES cars(plate_number)
);