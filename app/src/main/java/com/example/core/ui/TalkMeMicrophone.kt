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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.repository.SpeechRecognitionState
import kotlin.math.sin

val TalkMePrimaryColor = Color(0xFF1E3A8A)
val TalkMeSecondaryColor = Color(0xFF0D9488)
val TalkMeAccentColor = Color(0xFFF59E0B)
val TalkMeErrorColor = Color(0xFFEF4444)
val TalkMeSurfaceColor = Color(0xFFFFFFFF)

/**
 * Premium Jetpack Compose Microphone Component featuring:
 * - Animated microphone button with pulsing aura during active recording.
 * - Real-time voice waveform visualization powered by actual audio amplitude.
 * - Live interim and final offline STT transcription display.
 * - Interactive controls (Stop, Cancel, Retry).
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
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        color = TalkMeSurfaceColor,
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Real-Time STT Transcription Text Display
            val displayText = when {
                finalText.isNotBlank() -> finalText
                interimText.isNotBlank() -> interimText
                speechState == SpeechRecognitionState.LISTENING_PASS_1 -> "Eshitilmoqda (Inglizcha gapiring)…"
                speechState == SpeechRecognitionState.PROCESSING_PASS_2 -> "Oflayn STT Pass-2 tahlil qilinmoqda…"
                else -> "Tugmani bosib inglizcha gapiring"
            }

            Text(
                text = displayText,
                fontSize = 15.sp,
                fontWeight = if (finalText.isNotBlank()) FontWeight.Bold else FontWeight.Normal,
                color = if (finalText.isNotBlank()) TalkMePrimaryColor else Color(0xFF475569),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
            )

            if (speechState == SpeechRecognitionState.LISTENING_PASS_1) {
                val mins = durationSeconds / 60
                val secs = durationSeconds % 60
                Text(
                    text = String.format("● REC %02d:%02d", mins, secs),
                    color = TalkMeErrorColor,
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
                    // Cancel Button
                    IconButton(
                        onClick = onCancelRecording,
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF1F5F9))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Cancel",
                            tint = Color(0xFF64748B)
                        )
                    }

                    Spacer(modifier = Modifier.width(24.dp))
                }

                // Main Pulsing Mic Button
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
                                .background(TalkMeSecondaryColor.copy(alpha = 0.25f))
                        )
                    }

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(60.dp)
                            .scale(animatedScale)
                            .clip(CircleShape)
                            .background(
                                if (isListening) TalkMeSecondaryColor else TalkMePrimaryColor
                            )
                            .clickable {
                                if (isListening) onStopRecording() else onStartRecording()
                            }
                    ) {
                        Icon(
                            imageVector = if (isListening) Icons.Default.Stop else Icons.Default.Mic,
                            contentDescription = "Microphone",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }

                if (speechState == SpeechRecognitionState.LISTENING_PASS_1) {
                    Spacer(modifier = Modifier.width(24.dp))

                    // Done / Finish Button
                    IconButton(
                        onClick = onStopRecording,
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(TalkMeSecondaryColor)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Done",
                            tint = Color.White
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
    modifier: Modifier = Modifier
) {
    val barsCount = 20
    val activeColor = TalkMeSecondaryColor
    val inactiveColor = Color(0xFFE2E8F0)

    Canvas(modifier = modifier) {
        val barWidth = 6.dp.toPx()
        val gap = 4.dp.toPx()
        val totalWidth = barsCount * barWidth + (barsCount - 1) * gap
        val startX = (size.width - totalWidth) / 2f
        val centerY = size.height / 2f
        val maxHeight = size.height * 0.8f

        for (i in 0 until barsCount) {
            val factor = sin(i * 0.5f + System.currentTimeMillis() * 0.005f) * 0.5f + 0.5f
            val barHeight = (maxHeight * (amplitude * 0.7f + factor * 0.3f)).coerceAtLeast(8.dp.toPx())
            val x = startX + i * (barWidth + gap)
            val top = centerY - barHeight / 2f

            drawRoundRect(
                color = if (amplitude > 0.05f) activeColor else inactiveColor,
                topLeft = Offset(x, top),
                size = Size(barWidth, barHeight),
                cornerRadius = CornerRadius(barWidth / 2f, barWidth / 2f)
            )
        }
    }
}
