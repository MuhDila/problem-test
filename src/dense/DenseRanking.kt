package dense/*

2. Dense Ranking (Score: 30)
GITS sedang bermain permainan arcade, dan dalam setiap permainan GITS ingin naik ke peringkat tertinggi dan
juga ingin mengetahui setiap peringkat di setiap permainan. Dalam permainan ini menggunakan skema Dense Ranking dan
memiliki aturan sebagai berikut:
- Peringkat pertama dapat diraih oleh pemain yang memiliki skor tertinggi
- Pemain yang memiliki skor yang sama memiliki peringkat yang sama.

Contoh:
- Empat pemain memiliki skor tertinggi sebagai berikut 100, 80, 80, dan 70, maka masing-masing pemain itu memiliki rangking 1, 2, 2, dan 3.
- Jika GITS memiliki skor 60, 70, 100 setelah pertandingan maka rangking yang didapatkan adalah 4, 3, dan 1.

Sampel Input:
7
100 100 50 40 40 20 10
4
5 25 50 120

Sampel Output:
6 4 2 1

Keterangan:
- 7 adalah bentuk bilangan bulat, angka yang menunjukkan pada jumlah pemain yang ikut serta.
- 100 100 50 40 40 20 10 adalah daftar skor yang diurutkan dari nilai terbesar ke nilai terkecil (dalam bentuk array integer).
- 4 adalah jumlah permainan yang diikuti oleh GITS.
- 5 25 50 120 adalah skor yang didapatkan oleh GITS.

Soal:
Buat fungsi yang digunakan untuk menyelesaikan permasalahan Dense Ranking!

*/

private fun denseRankSimple(s: Int, board: IntArray): Int {
    // mulai dari rank 1 (paling tinggi)
    var rank = 1
    var prev = Int.MIN_VALUE

    // scan semua skor di leaderboard
    for (v in board) {
        // kalau ketemu nilai unik yang beda dari sebelumnya
        if (v != prev) {
            // kalau skor GITS >= nilai unik ini, berarti posisinya di rank saat ini
            if (s >= v) return rank
            // update nilai unik terakhir dan geser rank ke bawah
            prev = v
            rank++
        }
    }
    return rank
}

fun main() {
    print("Silahkan masukan jumlah pemain (n) : ")
    val n = readlnOrNull()?.trim()?.toIntOrNull()
    if (n == null || n < 1) {
        println("Input n tidak valid. n harus >= 1.")
        return
    }

    print("Silahkan masukan $n skor leaderboard : ")
    val boardTokens = readlnOrNull()?.trim()?.split(Regex("\\s+")) ?: emptyList()
    if (boardTokens.size < n) {
        println("Jumlah skor leaderboard kurang dari $n.")
        return
    }

    // ubah token jadi IntArray; kalau ada yang bukan angka, tandai Int.MIN_VALUE
    val rawBoard = IntArray(n) { i -> boardTokens[i].toIntOrNull() ?: Int.MIN_VALUE }
    if (rawBoard.any { it == Int.MIN_VALUE }) {
        println("Terdapat skor leaderboard yang bukan bilangan bulat.")
        return
    }

    // sort ke descending
    val board = rawBoard.sortedArrayDescending()

    print("Silahkan masukan jumlah skor GITS (m) : ")
    val m = readlnOrNull()?.trim()?.toIntOrNull()
    if (m == null || m < 1) {
        println("Input m tidak valid. m harus >= 1.")
        return
    }

    print("Silahkan masukan $m skor GITS : ")
    val gitsTokens = readlnOrNull()?.trim()?.split(Regex("\\s+")) ?: emptyList()
    if (gitsTokens.size < m) {
        println("Jumlah skor GITS kurang dari $m.")
        return
    }

    // ubah token jadi IntArray; kalau ada yang bukan angka, tandai Int.MIN_VALUE
    val gits = IntArray(m) { i -> gitsTokens[i].toIntOrNull() ?: Int.MIN_VALUE }
    if (gits.any { it == Int.MIN_VALUE }) {
        println("Terdapat skor GITS yang bukan bilangan bulat.")
        return
    }

    // hitung rank untuk tiap skor GITS
    val ranks = IntArray(m) { i -> denseRankSimple(gits[i], board) }

    print("Output yang dihasilkan : ")
    println(ranks.joinToString(" "))
}