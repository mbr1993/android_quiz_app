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
            )
        )
    }
}