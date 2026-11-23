package uz.talkme.next.data

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import uz.talkme.next.audio.LocalSpeechOutput
import uz.talkme.next.audio.WhisperSpeechInput
import uz.talkme.next.domain.SpeechInput
import uz.talkme.next.domain.SpeechOutput
import uz.talkme.next.domain.TutorClient

@Module
@InstallIn(ViewModelComponent::class)
object VoiceModule {
    @Provides @ViewModelScoped
    fun input(@ApplicationContext context: Context): SpeechInput = WhisperSpeechInput(context)
    @Provides @ViewModelScoped
    fun output(@ApplicationContext context: Context): SpeechOutput = LocalSpeechOutput(context)
    @Provides @ViewModelScoped
    fun tutor(): TutorClient = GeminiTutorClient()
}
