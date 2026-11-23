package com.example.domain.model

/**
 * Platform-independent curriculum contract for the future AI Classroom.
 * This file does not enable live audio, a 3D renderer, or premium billing.
 */
enum class ClassroomLanguage { UZ, EN }
enum class ClassroomStage { RULE, EXAMPLES, EXERCISES, WRAP_UP }
enum class ClassroomExerciseKind { READING, WRITING, LISTENING, SPEAKING }

data class ClassroomExplanation(
    val id: String,
    val uzbek: String,
    val english: String,
    val highlightedEnglish: String
) {
    fun text(language: ClassroomLanguage): String =
        if (language == ClassroomLanguage.UZ) uzbek else english
}

data class ClassroomExample(
    val english: String,
    val uzbek: String
)

data class ClassroomExercise(
    val id: String,
    val kind: ClassroomExerciseKind,
    val promptEnglish: String,
    val promptUzbek: String,
    val expectedAnswer: String
)

data class AiClassroomLesson(
    val id: String,
    val level: String,
    val prerequisiteIds: List<String>,
    val title: String,
    val explanations: List<ClassroomExplanation>,
    val examples: List<ClassroomExample>,
    val exercises: List<ClassroomExercise>,
    val wrapUpUzbek: String,
    val wrapUpEnglish: String,
    val relatedRolePlayIds: List<String>
)

data class ClassroomResumePoint(
    val lessonId: String,
    val stage: ClassroomStage,
    val exerciseId: String? = null
)

data class RolePlayScenario(
    val id: String,
    val journeyId: String,
    val level: String,
    val title: String,
    val learnerGoal: String,
    val characterRole: String,
    val openingEnglish: String,
    val hintUzbek: String,
    val prerequisiteLessonIds: List<String> = emptyList()
)

/** Lesson content is reusable in text mode and in a future 3D tutor UI. */
object AiClassroomStarterContent {
    val lesson02 = AiClassroomLesson(
        id = "en-a0-002",
        level = "A0",
        prerequisiteIds = listOf("en-a0-001"),
        title = "Nice to Meet You!",
        explanations = listOf(
            ClassroomExplanation("you", "I — men. You — sen yoki siz.", "I means me. You means the person you speak to.", "I → You"),
            ClassroomExplanation("you_are", "I bilan am, you bilan are ishlatiladi. You am noto‘g‘ri.", "Say I am, but you are. Do not say you am.", "I am → You are"),
            ClassroomExplanation("are_you", "Savolda are so‘zi you dan oldin keladi: Are you Ali?", "To ask a question, put are before you: Are you Ali?", "Are you Ali?"),
            ClassroomExplanation("your_name", "Ism so‘rash: What's your name? Javob: My name is Ali.", "Ask What's your name? Answer My name is Ali.", "What's your name?")
        ),
        examples = listOf(
            ClassroomExample("I am Ali.", "Men Aliman."),
            ClassroomExample("You are Lera.", "Sen Lerassan."),
            ClassroomExample("Are you Ali?", "Sen Alimisan?"),
            ClassroomExample("Yes, I am.", "Ha, men Aliman."),
            ClassroomExample("No, I'm not.", "Yo‘q, men emasman."),
            ClassroomExample("What's your name?", "Isming nima?")
        ),
        exercises = listOf(
            ClassroomExercise("reading_01", ClassroomExerciseKind.READING, "Which sentence is a question?", "Savol gapni tanlang.", "Are you Ali?"),
            ClassroomExercise("writing_01", ClassroomExerciseKind.WRITING, "You ___ Lera.", "Bo‘sh joyni to‘ldiring.", "are"),
            ClassroomExercise("listening_01", ClassroomExerciseKind.LISTENING, "What's your name?", "Eshitgan savolga javob bering.", "My name is Ali."),
            ClassroomExercise("speaking_01", ClassroomExerciseKind.SPEAKING, "Are you Ali?", "Leraga javob bering.", "Yes, I am.")
        ),
        wrapUpUzbek = "Bugun I am, You are, Are you...? va What's your name? ni o‘rgandingiz.",
        wrapUpEnglish = "You can introduce yourself and ask someone's name.",
        relatedRolePlayIds = listOf("travel_001")
    )

    val travelStarter = listOf(
        RolePlayScenario("travel_001", "travel_abroad", "A0", "Hello, I'm a Traveler!", "Introduce yourself", "Travel assistant", "Hello! What's your name?", "My name is ... deb javob bering.", listOf("en-a0-002")),
        RolePlayScenario("travel_005", "travel_abroad", "A1", "Taxi to the Airport", "Ask for a ride to the airport", "Taxi driver", "Hello! Where to?", "To the airport, please. deb ayting."),
        RolePlayScenario("travel_006", "travel_abroad", "A1", "Airport Check-in", "Check in and hand over luggage", "Check-in agent", "May I see your passport?", "Here you are. deb javob bering."),
        RolePlayScenario("travel_015", "travel_abroad", "A1", "Hotel Check-in", "Confirm a reservation", "Receptionist", "Hello! Do you have a reservation?", "Yes, I have a reservation. deb ayting.")
    )
}
