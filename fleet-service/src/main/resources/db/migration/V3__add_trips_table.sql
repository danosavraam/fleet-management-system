CREATE TABLE trips (
  id BIGSERIAL PRIMARY KEY,
  driver_id BIGINT NOT NULL,
  car_id VARCHAR(255) NOT NULL,
  start_time TIMESTAMP NOT NULL,
  end_time TIMESTAMP,
  distance_km DOUBLE PRECISION,
  CONSTRAINT fk_trip_driver FOREIGN KEY (driver_id) REFERENCES drivers(personal_id),
  CONSTRAINT fk_trip_car FOREIGN KEY (car_id) REFERENCES cars(plate_number)
);