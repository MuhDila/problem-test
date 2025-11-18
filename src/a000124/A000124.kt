package a000124/*

1. A000124 of Sloane’s OEIS (Score: 20)
Buatlah sebuah program dengan output sebagai berikut.
Input bisa dinamis yang menghasilkan output yang berbeda-beda sesuai input yang dimasukan.
Gunakan rumus A000124 of Sloane’s OEIS.

Contoh:
- Input: 7
- Output : 1-2-4-7-11-16-22

Soal:
Buat fungsi untuk menyelesaikan rumus A000124 of Sloane’s OEIS!

*/

fun main() {
    print("Silahkan masukan inputan : ")
    val n = readlnOrNull()?.trim()?.toIntOrNull()
    if (n == null || n < 1) {
        println("Input harus >= 1")
        return
    }

    val parts = ArrayList<String>(n)
    for (k in 1..n) {
        val term = 1 + (k * (k - 1)) / 2
        parts.add(term.toString())
    }

    print("Output yang dihasilkan : ")
    print(parts.joinToString("-"))
}