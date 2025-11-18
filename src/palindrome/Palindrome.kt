package palindrome/*

3. Highest Palindrome (Score: 50)
Diberikan sebuah string yang merepresentasikan sebuah angka, tujuan utamanya adalah untuk menemukan palindrom terbesar
yang bisa dibentuk dengan mengubah maksimal 'k' digit dalam string tersebut.
Palindrom adalah angka yang terbaca sama baik dari depan maupun dari belakang.
Kamu diberikan sebuah string 's' yang merepresentasikan sebuah angka dan sebuah bilangan bulat 'k'.
Tugas kamu adalah menemukan Highest Palindrome yang dapat dibentuk dengan mengubah paling banyak 'k' digit pada string 's'.

Sampel 1:
Input:
string: 3943
k: 1
palindrom:
1. 3943  => 3993
2. 3943 => 3443
Output: 3993
Penjelasan: Dari bentuk palindrom yang diperoleh maka highest palindrome-nya adalah 3993 dikarenakan 3993 > 3443.

Sampel 2:
Input:
string: 932239
k: 2
palindrom:
1. 932239  => sudah palindrome
2. Perlu replacement sebanyak k = 2 untuk mendapatkan nilai tertinggi => 992299
Output: 992299
Penjelasan: Dari bentuk palindrom yang diperoleh maka highest palindrome-nya adalah 992299 dikarenakan 992299 > 932239.

Aturan:
1. Jika dari sebuah string tidak ditemukan bentuk palindrome-nya meski sudah melakukan replacement dan tidak merepresentasikan
   sebuah angka maka akan mengeluarkan output -1.
2. Tidak boleh menggunakan fungsi bawaan/tools untuk pencarian/filter/sort.
3. Tidak boleh menggunakan looping.
4. Hanya diperkenankan menggunakan rekursif.

Soal:
Buat fungsi yang digunakan untuk menyelesaikan permasalahan Highest Palindrome!

*/

private fun isDigitsRec(s: String, i: Int = 0): Boolean {
    // cek semua char adalah digit (rekursif dari kiri ke kanan)
    if (i == s.length) return true
    val c = s[i]
    return (c in '0'..'9') && isDigitsRec(s, i + 1)
}

private fun makePalindromeRec(
    a: CharArray,
    i: Int,
    j: Int,
    k: Int,
    changed: BooleanArray
): Int {
    // ubah jadi palindrome
    if (i >= j) return k
    return if (a[i] == a[j]) {
        // kalau sama maka lanjut ke dalam tanpa kurangi k
        makePalindromeRec(a, i + 1, j - 1, k, changed)
    } else {
        // kalau beda maka harus samakan, kalau k habis maka gagal
        if (k <= 0) return -1
        if (a[i] > a[j]) a[j] = a[i] else a[i] = a[j]
        changed[i] = true
        changed[j] = true
        makePalindromeRec(a, i + 1, j - 1, k - 1, changed)
    }
}

private fun maximizeRec(
    a: CharArray,
    i: Int,
    j: Int,
    k: Int,
    changed: BooleanArray
): Int {
    // kalau sudah palindrome maka sekarang maksimalkan jadi '9' selama k cukup
    if (i > j) return k            // lewat tengah maka selesai
    if (i == j) {                  // tengah (panjang ganjil)
        if (k > 0 && a[i] != '9') {
            a[i] = '9'
            return k - 1
        }
        return k
    }

    // kalau dua-duanya sudah '9' dilewat
    if (a[i] == '9' && a[j] == '9') {
        return maximizeRec(a, i + 1, j - 1, k, changed)
    }

    // bikin (i,j) jadi '9'
    val cost = if (changed[i] || changed[j]) 1 else 2

    return if (k >= cost) {
        a[i] = '9'
        a[j] = '9'
        maximizeRec(a, i + 1, j - 1, k - cost, changed)
    } else {
        // k tidak cukup maka skip
        maximizeRec(a, i + 1, j - 1, k, changed)
    }
}

private fun highestPalindrome(s: String, k: Int): String {
    // Validasi dasar
    if (s.isEmpty()) return "-1"
    if (k < 0) return "-1"
    if (!isDigitsRec(s)) return "-1"  // harus semua digit

    val a = s.toCharArray()
    val changed = BooleanArray(a.size)

    // ubah jadi palindrome minimal
    val kAfter = makePalindromeRec(a, 0, a.lastIndex, k, changed)
    if (kAfter < 0) return "-1"       // gagal jadi palindrome

    // ubah jadi palindrome maksimalkan ke '9' selama k cukup
    maximizeRec(a, 0, a.lastIndex, kAfter, changed)

    return String(a)
}

fun main() {
    print("Masukkan string angka (s) : ")
    val s = readlnOrNull()?.trim().orEmpty()
    print("Masukkan k (maks perubahan) : ")
    val k = readlnOrNull()?.trim()?.toIntOrNull() ?: -1

    val result = highestPalindrome(s, k)
    print("Output : ")
    println(result)
}