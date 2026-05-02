const { db, initDb } = require("./db");

const runSeeder = async () => {
  try {
    await initDb();

    const createTableQuery = `
            CREATE TABLE IF NOT EXISTS bucin (
                id INT AUTO_INCREMENT PRIMARY KEY,
                pasangan1 VARCHAR(255),
                pasangan2 VARCHAR(255),
                tingkatAnomali VARCHAR(100),
                deskripsi TEXT
            )
        `;

    db.query(createTableQuery, (err) => {
      if (err) {
        console.error("Gagal membuat:", err.message);
        return db.end();
      }

      const insertQuery =
        "INSERT INTO bucin (pasangan1, pasangan2, tingkatAnomali, deskripsi) VALUES ?";
      const values = [
        ["Acong", "RRG", "Bucin 😍", "Physical touch."],
        ["Acong", "Yoga", "Whattt 🤯", "......"],
      ];

      db.query(insertQuery, [values], (err, results) => {
        if (err) {
          console.error("Gagal memasukkan data:", err.message);
        } else {
          console.log("Data berhasil di-seed!");
        }

        db.end();
      });
    });
  } catch (error) {
    console.error("Seeder dihentikan karena:", error);
    db.end();
  }
};

runSeeder();
