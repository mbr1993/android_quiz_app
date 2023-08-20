package uz.mbr.quiz.datasource

import uz.mbr.quiz.data.Question

class QuestionDataSource {
    fun getQuestionData(): MutableList<Question> {
        return mutableListOf(
            Question(
                "Hijr surasi qayerda nozil bo'lgan va necha oyatdan iborat?",
                "Makkada, 100 oyat",
                "Madinada, 99 oyat",
                "Makkada, 99 oyat",
                "Makkada, 99 oyat"
            ),
            Question(
                "Nabiy sollallohu alayhi vassallam Hasson ibn Sobit roziyallohu anhuga hajv aytishlarini amr qila turib, u kishining haqlariga duo qiladilar. O'sha duo qanday edi",
                "Allohim, uni Ruhul Qudus ila qo'llagin",
                "Allohim, O'zing uni muhofaza qilgin",
                "Allohim, unga madad bergin",
                "Allohim, uni Ruhul Qudus ila qo'llagin"
            ),
            Question(
                "Rosululloh sollallohu alayhi vassallamni ilk muazzinlari kim edi?",
                "ikkisi ham to'g'ri",
                "Abdulloh ibn Ummu Maktum roziyallohu anhu",
                "Bilol roziyallohu anhu",
                "Bilol roziyallohu anhu"
            ),
            Question(
                "Niyat shariatda - Alloh taoloning roziligini talab qilib va Uning hukmiga bo'ysunib bir ishni qilishga yuzlangan irodadir. Yuqoridagi ta'rif kim tamonidan aytilgan?",
                "Ubaydulloh ibn Mas'ud",
                "Abu Hanifa",
                "Qozi Bayzoviy",
                "Qozi Bayzoviy"
            ),
            Question(
                "\"Al-Munqiz minaz zolal nomli\" kitobning muallifi kim?",
                "Shayx Kamoliddin Dumariy",
                "Xorun ar-Rashid",
                "Imom G'azzoliy",
                "Imom G'azzoliy"
            ),
            Question(
                "Tog'ning asalidan ushr beriladimi?",
                "beriladi",
                "berilmaydi",
                "yegulik bo'lganligi uchun ixtiyoriy", "beriladi"
            ),
            Question(
                "\"Zikr\" so'zi Qur'oni Karimning ismlaridan biri hisoblanadimi?",
                "Yo'q", "Ha", "Endi bilib olaman", "Ha"
            ),
            Question(
                "Har safar vahiy nozil bo'lgandan so'ng, Muhammad sollallohu alayhi vassallam kimlarni chaqirar edilar?",
                "Kotiblarni", "Ahli baytlarini", "Yodlab oluvchilarni",
                "Kotiblarni"
            ),
            Question(
                "Haj amallari qatoridagi Sa'y orasida yurish qayerda tugaydi?",
                "Safoda", "Muzdalifada", "Marvada", "Marvada"
            ),
            Question(
                "Qur'oni Karimdagi eng qisqa sura qaysi?", "Asr",
                "Kavsar", "Nasr", "Kavsar"
            )
        )
    }
}