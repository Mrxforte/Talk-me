package com.example.core.ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.R
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.speech.VadState
import com.example.domain.repository.SpeechRecognitionState
import java.util.Locale
import kotlin.math.sin

@Composable
private fun micColor(resource: Int): Color = Color(ContextCompat.getColor(LocalContext.current, resource))

/**
 * Jetpack Compose Microphone Component featuring:
 * - Animated microphone button with pulsing aura during active recording.
 * - Real-time voice waveform visualization powered by actual audio amplitude and Silero VAD state.
 * - Live interim and final local Sherpa-ONNX STT transcription display.
 * - Interactive controls (Start, Stop, Cancel).
 */
@Composable
fun TalkMeMicrophone(
    speechState: SpeechRecognitionState,
    amplitude: Float,
    interimText: String,
    finalText: String,
    durationSeconds: Int,
    onStartRecording: () -> Unit,
    onStopRecording: () -> Unit,
    onCancelRecording: () -> Unit,
    modifier: Modifier = Modifier,
    vadState: VadState = VadState.SILENCE
) {
    val primaryColor = micColor(R.color.color_primary)
    val secondaryColor = micColor(R.color.color_secondary)
    val onPrimaryColor = micColor(R.color.color_text_on_primary)
    val onSurfaceColor = micColor(R.color.color_on_surface)
    val mutedColor = micColor(R.color.color_on_surface_secondary)

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = micColor(R.color.color_card_surface),
        shadowElevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Live Waveform Visualizer
            if (speechState == SpeechRecognitionState.LISTENING_PASS_1) {
                AudioWaveformBars(
                    amplitude = amplitude,
                    isSpeechActive = vadState == VadState.SPEECH_BEGIN ||
                        vadState == VadState.ACTIVE_SPEECH ||
                        vadState == VadState.PAUSE,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Real-Time Local STT Transcription Text Display
            val displayText = when {
                finalText.isNotBlank() -> finalText
                interimText.isNotBlank() -> interimText
                speechState == SpeechRecognitionState.LISTENING_PASS_1 &&
                    (vadState == VadState.SPEECH_BEGIN || vadState == VadState.ACTIVE_SPEECH) ->
                    "Nutq aniqlanmoqda (Silero VAD + Sherpa-ONNX)…"
                speechState == SpeechRecognitionState.LISTENING_PASS_1 ->
                    "Eshitilmoqda (Inglizcha gapiring)…"
                speechState == SpeechRecognitionState.PROCESSING_PASS_2 ->
                    "Oflayn Sherpa-ONNX Pass-2 yakunlanmoqda…"
                else -> "Tugmani bir marta bosib suhbatni boshlang"
            }

            Text(
                text = displayText,
                fontSize = 15.sp,
                fontWeight = if (finalText.isNotBlank()) FontWeight.Bold else FontWeight.Normal,
                color = if (finalText.isNotBlank()) onSurfaceColor else mutedColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .testTag("talkme_mic_status_text")
            )

            if (speechState == SpeechRecognitionState.LISTENING_PASS_1) {
                val mins = durationSeconds / 60
                val secs = durationSeconds % 60
                Text(
                    text = String.format(Locale.US, "● REC %02d:%02d", mins, secs),
                    color = micColor(R.color.color_error),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Microphone Controls Dock
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                if (speechState == SpeechRecognitionState.LISTENING_PASS_1) {
                    IconButton(
                        onClick = onCancelRecording,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(micColor(R.color.color_card_surface_subtle))
                            .testTag("talkme_mic_cancel_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Cancel",
                            tint = mutedColor
                        )
                    }

                    Spacer(modifier = Modifier.width(24.dp))
                }

                val isListening = speechState == SpeechRecognitionState.LISTENING_PASS_1
                val animatedScale by animateFloatAsState(
                    targetValue = if (isListening) 1f + (amplitude * 0.3f) else 1f,
                    label = "micScale"
                )

                val infiniteTransition = rememberInfiniteTransition(label = "pulse")
                val pulseGlow by infiniteTransition.animateFloat(
                    initialValue = 1f,
                    targetValue = 1.3f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(800, easing = FastOutSlowInEasing),
                        repeatMode = RepeatMode.Reverse
                    ),
                    label = "pulseGlow"
                )

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.size(72.dp)
                ) {
                    if (isListening) {
                        Box(
                            modifier = Modifier
                                .size(64.dp)
                                .scale(pulseGlow)
                                .clip(CircleShape)
                                .background(secondaryColor.copy(alpha = 0.25f))
                        )
                    }

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(60.dp)
                            .scale(animatedScale)
                            .clip(CircleShape)
                            .background(
                                if (isListening) secondaryColor else primaryColor
                            )
                            .clickable {
                                if (isListening) onStopRecording() else onStartRecording()
                            }
                            .testTag("talkme_mic_primary_button")
                    ) {
                        Icon(
                            imageVector = if (isListening) Icons.Default.Stop else Icons.Default.Mic,
                            contentDescription = "Microphone",
                            tint = onPrimaryColor,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }

                if (speechState == SpeechRecognitionState.LISTENING_PASS_1) {
                    Spacer(modifier = Modifier.width(24.dp))

                    IconButton(
                        onClick = onStopRecording,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(secondaryColor)
                            .testTag("talkme_mic_done_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Done",
                            tint = onPrimaryColor
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AudioWaveformBars(
    amplitude: Float,
    isSpeechActive: Boolean,
    modifier: Modifier = Modifier
) {
    val barsCount = 20
    val activeColor = micColor(R.color.color_secondary)
    val inactiveColor = micColor(R.color.color_cosmic_track)

    Canvas(modifier = modifier) {
        val barWidth = 6.dp.toPx()
        val gap = 4.dp.toPx()
        val totalWidth = barsCount * barWidth + (barsCount - 1) * gap
        val startX = (size.width - totalWidth) / 2f
        val centerY = size.height / 2f
        val maxHeight = size.height * 0.8f

        for (i in 0 until barsCount) {
            val factor = sin(i * 0.5f) * 0.5f + 0.5f
            val barHeight = (maxHeight * (amplitude * 0.7f + factor * 0.3f)).coerceAtLeast(8.dp.toPx())
            val x = startX + i * (barWidth + gap)
            val top = centerY - barHeight / 2f

            drawRoundRect(
                color = if (isSpeechActive || amplitude > 0.05f) activeColor else inactiveColor,
                topLeft = Offset(x, top),
                size = Size(barWidth, barHeight),
                cornerRadius = CornerRadius(barWidth / 2f, barWidth / 2f)
            )
        }
    }
}
