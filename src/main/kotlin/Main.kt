import java.util.*

data class Mahasiswa(var nim: Int, var nama: String)

class UserInput {
    var nama: String = ""
        get() = field.uppercase()
        set(value) {
            field = value.trim()
        }
}

class DataHandle {
    private val list = ArrayList<Mahasiswa>()
    private val scanner = Scanner(System.`in`)

    fun tambahData() {
        print("NIM: ")
        val nim = inputInt()

        print("Nama: ")
        val inputNama: String? = scanner.nextLine()

        val user = UserInput()
        user.nama = inputNama ?: "Tidak diketahui."

        list.add(Mahasiswa(nim, user.nama))
        println("Data berhasil ditambahkan.")
    }

    fun listData() {
        if (list.isEmpty()) {
            println("Data kosong.")
        } else {
            println("\nLIST DATA")
            list.forEachIndexed { index, data ->
                println("$index. NIM: ${data.nim}, Nama: ${data.nama}")
            }
        }
    }

    fun editData() {
        listData()
        print("Pilih: ")
        val index = inputInt()

        if (index in list.indices) {
            print("Update nama: ")
            val namaBaru: String? = scanner.nextLine()

            val user = UserInput()
            user.nama = namaBaru ?: list[index].nama

            list[index].nama = user.nama
            println("Data berhasil diupdate.")
        } else {
            println("Tidak valid.")
        }
    }

    fun hapusData() {
        listData()
        print("Pilih: ")
        val index = inputInt()

        if (index in list.indices) {
            list.removeAt(index)
            println("Data berhasil dihapus.")
        } else {
            println("Tidak valid.")
        }
    }

    private fun inputInt(): Int {
        return try {
            val value = scanner.nextInt()
            scanner.nextLine()
            value
        } catch (e: Exception) {
            scanner.nextLine()
            println("Input harus angka.")
            inputInt()
        }
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val manager = DataHandle()

    while (true) {
        println("\nMENU")
        println("1. Tambah Data")
        println("2. List Data")
        println("3. Edit Data")
        println("4. Hapus Data")
        println("0. Exit")
        print("Pilih: ")

        when (try {
            scanner.nextInt()
        } catch (e: Exception) {
            scanner.nextLine()
            -1
        }) {
            1 -> manager.tambahData()
            2 -> manager.listData()
            3 -> manager.editData()
            4 -> manager.hapusData()
            0 -> {
                println("Terima Kasih.")
                break
            }
            else -> println("Pilihan tidak valid.")
        }
    }
}