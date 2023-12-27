-- Create a common Users table
DROP TABLE IF EXISTS applications;
DROP TABLE IF EXISTS jobs;

DROP TABLE IF EXISTS workers;
DROP TABLE IF EXISTS employers;
DROP TABLE IF EXISTS admins;

DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Create a Workers table with foreign key reference to Users
CREATE TABLE IF NOT EXISTS workers (
    id INT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    bio TEXT,
    picture BLOB,
    cv BLOB,

    FOREIGN KEY (id) REFERENCES users(id)
);

-- Create an Employers table with foreign key reference to Users
CREATE TABLE IF NOT EXISTS employers (
     id INT PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     description TEXT,
     picture BLOB,

     FOREIGN KEY (id) REFERENCES users(id)
);

-- Create an Admins table with foreign key reference to Users
CREATE TABLE IF NOT EXISTS admins (
    id INT PRIMARY KEY,
    role VARCHAR(50) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,

    FOREIGN KEY (id) REFERENCES users(id)
);


-- Create Jobs table
CREATE TABLE IF NOT EXISTS jobs (
    id INT PRIMARY KEY AUTO_INCREMENT,
    employer_id INT,
    description TEXT NOT NULL,
    external_link VARCHAR(255),
    posted_date DATE NOT NULL,

    FOREIGN KEY (employer_id) REFERENCES employers(id)
);

-- Create Applications table to represent user applications for jobs
CREATE TABLE IF NOT EXISTS applications (
    id INT PRIMARY KEY AUTO_INCREMENT,
    worker_id INT,
    job_id INT,
    application_date DATE NOT NULL,

    FOREIGN KEY (worker_id) REFERENCES workers(id),
    FOREIGN KEY (job_id) REFERENCES jobs(id)
);

-- Insert data into users table
INSERT INTO users (username, password) VALUES
   ('worker1', 'worker1pass'),
   ('employer1', 'employer1pass'),
   ('admin1', 'admin1pass');

-- Insert data into workers table
INSERT INTO workers (id, first_name, last_name, email, phone, bio, picture, cv) VALUES
    (1, 'John', 'Doe', 'john@example.com', '1234567890', 'Bio goes here', NULL, NULL);

-- Insert data into employers table
INSERT INTO employers (id, name, description, picture) VALUES
    (2, 'Company A', 'A leading technology company', NULL);

-- Insert data into admins table
INSERT INTO admins (id, role, first_name, last_name) VALUES
    (3, 'Super Admin', 'Admin', 'User');

-- Insert data into jobs table
INSERT INTO jobs (employer_id, description, external_link, posted_date) VALUES
    (2, 'Software Engineer', 'https://companyA.com/job1', '2023-01-01'),
    (2, 'Data Scientist', 'https://companyA.com/job2', '2023-01-02');

-- Insert data into applications table
INSERT INTO applications (worker_id, job_id, application_date) VALUES
    (1, 1, '2023-01-05'),
    (1, 2, '2023-01-08');
