const express = require("express");
const cors = require("cors");
const { db, initDb } = require("./db");

const app = express();
app.use(cors());
app.use(express.json());

// Fetch
app.get("/api/bucin", (req, res) => {
  db.query("SELECT * FROM bucin ORDER BY id DESC", (err, results) => {
    if (err) {
      console.error("Error GET /api/bucin:", err.message);
      return res.status(500).json({ error: err.message });
    }
    res.json(results);
  });
});

// Add
app.post("/api/bucin", (req, res) => {
  const { pasangan1, pasangan2, tingkatAnomali, deskripsi } = req.body;
  db.query(
    "INSERT INTO bucin (pasangan1, pasangan2, tingkatAnomali, deskripsi) VALUES (?, ?, ?, ?)",
    [pasangan1, pasangan2, tingkatAnomali, deskripsi],
    function (err, results) {
      if (err) {
        console.error("Error POST /api/bucin:", err.message);
        return res.status(500).json({ error: err.message });
      }
      res.json({
        id: results.insertId,
        pasangan1,
        pasangan2,
        tingkatAnomali,
        deskripsi,
      });
    },
  );
});

// Edit
app.put("/api/bucin/:id", (req, res) => {
  const { id } = req.params;
  const { pasangan1, pasangan2, tingkatAnomali, deskripsi } = req.body;
  db.query(
    "UPDATE bucin SET pasangan1 = ?, pasangan2 = ?, tingkatAnomali = ?, deskripsi = ? WHERE id = ?",
    [pasangan1, pasangan2, tingkatAnomali, deskripsi, id],
    function (err, results) {
      if (err) {
        console.error("Error PUT /api/bucin:", err.message);
        return res.status(500).json({ error: err.message });
      }
      if (results.affectedRows === 0)
        return res.status(404).json({ message: "Data tidak ditemui!" });
      res.json({
        id: Number(id),
        pasangan1,
        pasangan2,
        tingkatAnomali,
        deskripsi,
      });
    },
  );
});

// Delete
app.delete("/api/bucin/:id", (req, res) => {
  const { id } = req.params;
  db.query("DELETE FROM bucin WHERE id = ?", [id], function (err, results) {
    if (err) {
      console.error("Error DELETE /api/bucin:", err.message);
      return res.status(500).json({ error: err.message });
    }
    if (results.affectedRows === 0)
      return res.status(404).json({ message: "Data tidak ditemui!" });
    res.json({ message: "Data dihapus!" });
  });
});

const PORT = 3000;

initDb()
  .then(() => {
    app.listen(PORT, () => {
      console.log(`Server is running at http://localhost:${PORT}`);
    });
  })
  .catch((err) => {
    console.error("Server error:", err);
  });
