# 🧩 Tugas Pertemuan 14 — Sistem Manajemen Data Penduduk & Alamat

Aplikasi ini merupakan sistem manajemen data yang dibangun dengan **Java Swing**, **JPA (EclipseLink)**, dan **PostgreSQL**.  
Sistem ini memiliki fungsi utama untuk mengelola data:

- Penduduk
- Alamat
- Relasi Penduduk ↔ Alamat

Selain operasi CRUD, aplikasi ini juga mendukung **Import CSV**, **Export CSV**, dan **validasi relasi** agar data lebih konsisten.

---

## 🚀 Fitur Utama

### 1️⃣ CRUD Data Penduduk
- Tambah penduduk
- Edit penduduk
- Hapus penduduk
- Tampilkan daftar penduduk (JTable)
- Validasi: Penduduk harus memiliki alamat terdaftar

### 2️⃣ CRUD Data Alamat
- Tambah alamat baru
- Edit alamat
- Hapus alamat
- Data tersaji rapi di tabel

### 3️⃣ Relasi Penduduk—Alamat
- Relasi otomatis dengan JPA (One-to-Many / Many-to-One)
- Pilih alamat melalui ComboBox
- Tabel penduduk menampilkan alamat yang terhubung

---

## 🆕 Fitur Tambahan

### 📥 Import CSV
- Memilih file melalui JFileChooser
- Parsing CSV → Entity
- Validasi alamat berdasarkan id_alamat
- Jika alamat tidak ditemukan → data penduduk dilewati dan tampil warning

### 📤 Export CSV
- Ekspor data JTable menjadi file `.csv`
- Memudahkan untuk:
  - Backup data
  - Pengolahan di Excel / Google Sheets
  - Laporan cepat
---

## 🧪 LANGKAH-LANGKAH PRAKTIKUM

### 1️⃣ Membuat Database di PostgreSQL
- Buat database baru
- Buat tabel `alamat` & `penduduk` dengan relasi FK

### 2️⃣ Menghubungkan PostgreSQL ke NetBeans
- Tab **Services → Database**
- Add PostgreSQL driver & connect

### 3️⃣ Generate Entity Classes From Database
- New → *Entity Classes from Database*
- Pilih semua tabel
- Centang:
  - Generate Named Queries
  - Use column names in relationships

### 4️⃣ Buat Persistence Unit
- New → *Persistence Unit*
- Pilih koneksi PostgreSQL
- Selesai hingga `persistence.xml` terbentuk

### 5️⃣ Buat Package Kontainer
Berisi:
- JFrame Form
- Form Insert/Update/Delete (JDialog)
- Entity Classes
- Persistence Unit

### 6️⃣ Form Utama
Memuat:
- JTable untuk data penduduk + alamat
- Tombol Operasi:
  - Insert
  - Update
  - Delete
  - Import CSV
  - Export CSV

### 7️⃣ Insert Data
- Input JDialog
- Simpan 

### 8️⃣ Update Data
- Ambil data tabel
- Simpan perubahan 

### 9️⃣ Delete Data
- Hapus data tunggal atau semua data`

### 🔟 Import CSV
- JFileChooser → pilih file
- BufferedReader → baca CSV
- Validasi relasi alamat
- Insert otomatis ke database

### 1️⃣1️⃣ Export CSV
- Loop JTable → tulis ke FileWriter

### 1️⃣2️⃣ Print Report (Opsional)
- JasperReports → Cetak laporan tabel penduduk

---
