package com.example.feature.speaking

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Keyboard
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.MicOff
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

private val TalkMeRoyalBlue = Color(0xFF0EA5E9)
private val TalkMeDarkSlate = Color(0xFF0F172A)
private val TalkMeCosmicNavy = Color(0xFF101D36)
private val TalkMeNeonCyan = Color(0xFF00E5FF)
private val TalkMeErrorRed = Color(0xFFEF4444)

/**
 * Runtime Permission Gate for `Manifest.permission.RECORD_AUDIO`.
 * Ensures microphone permission is requested BEFORE starting local AudioRecord / Silero VAD / Sherpa-ONNX STT.
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MicrophonePermissionGate(
    onPermissionGranted: () -> Unit,
    onPermissionRevoked: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val accompanistMicPermission = rememberPermissionState(Manifest.permission.RECORD_AUDIO)

    var isDirectGranted by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            ) == PackageManager.PERMISSION_GRANTED
        )
    }
    var hasLaunchedInitialRequest by rememberSaveable { mutableStateOf(false) }
    var wasExplicitlyDenied by rememberSaveable { mutableStateOf(false) }

    val nativePermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        isDirectGranted = granted
        if (granted) {
            wasExplicitlyDenied = false
            onPermissionGranted()
        } else {
            wasExplicitlyDenied = true
            onPermissionRevoked()
        }
    }

    val effectiveGranted = accompanistMicPermission.status.isGranted || isDirectGranted

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        val checkNow = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.RECORD_AUDIO
        ) == PackageManager.PERMISSION_GRANTED
        isDirectGranted = checkNow
        if (checkNow) {
            wasExplicitlyDenied = false
            onPermissionGranted()
        }
    }

    LaunchedEffect(effectiveGranted) {
        if (effectiveGranted) {
            onPermissionGranted()
        } else if (!hasLaunchedInitialRequest) {
            hasLaunchedInitialRequest = true
            nativePermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

    AnimatedVisibility(
        visible = !effectiveGranted,
        enter = fadeIn(),
        exit = fadeOut(),
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xD90F172A))
                .padding(20.dp)
                .testTag("mic_permission_gate"),
            contentAlignment = Alignment.Center
        ) {
            IconButton(
                onClick = onNavigateBack,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 8.dp)
                    .size(48.dp)
                    .background(Color(0x33FFFFFF), CircleShape)
                    .testTag("permission_back_button")
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Orqaga qaytish",
                    tint = Color.White
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 420.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Surface(
                        modifier = Modifier.size(72.dp),
                        shape = CircleShape,
                        color = TalkMeRoyalBlue.copy(alpha = 0.12f)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Mic,
                                contentDescription = null,
                                tint = TalkMeRoyalBlue,
                                modifier = Modifier.size(36.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = "Mikrofon ruxsati kerak",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = TalkMeDarkSlate,
                            fontSize = 20.sp
                        ),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    val showRationale = accompanistMicPermission.status.shouldShowRationale
                    val descriptionText = if (wasExplicitlyDenied && !showRationale) {
                        "Speaking AI Tutor bilan ingliz tilida jonli gaplashish uchun ilova sozlamalaridan (Settings) mikrofon ruxsatini yoqing."
                    } else {
                        "Oflayn ovoz aniqlash (Silero VAD + Sherpa-ONNX) orqali AI Tutor bilan avtomatik gaplashish uchun mikrofonga ruxsat bering."
                    }

                    Text(
                        text = descriptionText,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFF475569),
                            fontSize = 14.5.sp,
                            lineHeight = 20.sp
                        ),
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    Button(
                        onClick = {
                            nativePermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .testTag("grant_mic_permission_button"),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = TalkMeRoyalBlue)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Mikrofonga ruxsat berish",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp
                        )
                    }

                    if (wasExplicitlyDenied) {
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedButton(
                            onClick = {
                                runCatching {
                                    val intent = Intent(
                                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                        Uri.fromParts("package", context.packageName, null)
                                    ).apply {
                                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    }
                                    context.startActivity(intent)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("open_app_settings_button"),
                            shape = RoundedCornerShape(14.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Sozlamalarni ochish (Settings)",
                                fontWeight = FontWeight.Medium,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Interactive Compose bar providing:
 * - Stop Conversation (`stop_conversation_button`)
 * - Mute / Unmute Microphone (`mute_microphone_button`)
 * - Cancel Active Turn / Skip TTS (`cancel_turn_button`)
 * - Retry Failed Turn / Replay TTS (`retry_turn_button`)
 * - Optional Normal Keyboard Input (`toggle_keyboard_input_button`, `keyboard_input_field`, `send_transcript_button`)
 */
@Composable
fun SpeakingAuxiliaryControlsBar(
    uiState: ConversationUiState,
    onKeyboardDraftChanged: (String) -> Unit,
    onSendKeyboardDraft: () -> Unit,
    onToggleKeyboardBar: (Boolean) -> Unit,
    onStopConversation: () -> Unit,
    onToggleMute: () -> Unit,
    onCancelTurnOrSpeech: () -> Unit,
    onRetryFailedTurn: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember { FocusRequester() }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .imePadding()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Error Banner with Retry / Replay / Skip Action
        AnimatedVisibility(visible = uiState.lastError != null) {
            val error = uiState.lastError
            if (error != null) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 6.dp)
                        .testTag("error_retry_banner"),
                    shape = RoundedCornerShape(14.dp),
                    color = Color(0xFFFEE2E2),
                    tonalElevation = 2.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = error.userMessageUzbek,
                            color = TalkMeErrorRed,
                            fontSize = 12.5.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.weight(1f)
                        )
                        if (error.isRecoverable) {
                            Spacer(modifier = Modifier.width(8.dp))
                            FilledTonalButton(
                                onClick = onRetryFailedTurn,
                                modifier = Modifier
                                    .height(38.dp)
                                    .testTag("retry_turn_button"),
                                colors = ButtonDefaults.filledTonalButtonColors(
                                    containerColor = TalkMeErrorRed,
                                    contentColor = Color.White
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "Qayta urinish",
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = if (error.code == ConversationError.ErrorCode.TTS_ERROR) {
                                        "Qayta eshitish"
                                    } else {
                                        "Qayta"
                                    },
                                    fontSize = 12.sp
                                )
                            }
                            if (error.code == ConversationError.ErrorCode.TTS_ERROR) {
                                Spacer(modifier = Modifier.width(6.dp))
                                OutlinedButton(
                                    onClick = onCancelTurnOrSpeech,
                                    modifier = Modifier
                                        .height(38.dp)
                                        .testTag("skip_tts_error_button")
                                ) {
                                    Text(text = "Skip", fontSize = 12.sp)
                                }
                            }
                        }
                    }
                }
            }
        }

        // 2. Action Controls: Stop, Mute, Cancel, and Separate Keyboard Input Toggle
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (uiState.isHandsFreeConversationActive ||
                uiState.isListeningOrSpeakingUser ||
                uiState.isFinalizing
            ) {
                FilledTonalButton(
                    onClick = onStopConversation,
                    modifier = Modifier
                        .height(38.dp)
                        .padding(end = 6.dp)
                        .testTag("stop_conversation_button"),
                    shape = RoundedCornerShape(19.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = Color(0xFF331522),
                        contentColor = Color(0xFFFCA5A5)
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Stop,
                        contentDescription = "Suhbatni to'xtatish",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "Stop",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                FilledTonalButton(
                    onClick = onToggleMute,
                    modifier = Modifier
                        .height(38.dp)
                        .padding(end = 6.dp)
                        .testTag("mute_microphone_button"),
                    shape = RoundedCornerShape(19.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = if (uiState.isMuted) Color(0xFF2B2111) else TalkMeCosmicNavy,
                        contentColor = if (uiState.isMuted) Color(0xFFFBBF24) else TalkMeNeonCyan
                    )
                ) {
                    Icon(
                        imageVector = if (uiState.isMuted) Icons.Default.MicOff else Icons.Default.Mic,
                        contentDescription = if (uiState.isMuted) "Ovozni yoqish" else "Ovozsiz qilish",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (uiState.isMuted) "Unmute" else "Mute",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            if (uiState.isLoading || uiState.isAiSpeaking) {
                FilledTonalButton(
                    onClick = onCancelTurnOrSpeech,
                    modifier = Modifier
                        .height(38.dp)
                        .padding(end = 6.dp)
                        .testTag("cancel_turn_button"),
                    shape = RoundedCornerShape(19.dp),
                    colors = ButtonDefaults.filledTonalButtonColors(
                        containerColor = TalkMeCosmicNavy,
                        contentColor = TalkMeNeonCyan
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Bekor qilish",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = if (uiState.isAiSpeaking) "Skip TTS" else "Cancel",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            FilledTonalButton(
                onClick = { onToggleKeyboardBar(!uiState.isKeyboardInputVisible) },
                modifier = Modifier
                    .height(38.dp)
                    .testTag("toggle_keyboard_input_button"),
                shape = RoundedCornerShape(19.dp),
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = TalkMeCosmicNavy,
                    contentColor = Color(0xFFCBD5E1)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Keyboard,
                    contentDescription = "Klaviatura",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = if (uiState.isKeyboardInputVisible) "Yopish" else "Klaviatura",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        // 3. Separate Normal Keyboard Input Alternative
        AnimatedVisibility(visible = uiState.isKeyboardInputVisible) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = uiState.keyboardDraftText,
                    onValueChange = onKeyboardDraftChanged,
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(focusRequester)
                        .testTag("keyboard_input_field"),
                    placeholder = {
                        Text(
                            text = "Ingliz tilida xabar yozing...",
                            fontSize = 13.5.sp,
                            color = Color(0xFF8E9CAE)
                        )
                    },
                    singleLine = false,
                    maxLines = 3,
                    shape = RoundedCornerShape(18.dp),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Send
                    ),
                    keyboardActions = KeyboardActions(
                        onSend = {
                            if (uiState.keyboardDraftText.isNotBlank() && !uiState.isLoading) {
                                onSendKeyboardDraft()
                            }
                        }
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = TalkMeNeonCyan,
                        unfocusedBorderColor = Color(0xFF28436E),
                        focusedContainerColor = Color(0xFF09142B),
                        unfocusedContainerColor = Color(0xFF09142B),
                        cursorColor = TalkMeNeonCyan
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    onClick = onSendKeyboardDraft,
                    enabled = uiState.keyboardDraftText.isNotBlank() && !uiState.isLoading,
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = if (uiState.keyboardDraftText.isNotBlank() && !uiState.isLoading) {
                                TalkMeRoyalBlue
                            } else {
                                Color(0xFFCBD5E1)
                            },
                            shape = CircleShape
                        )
                        .testTag("send_transcript_button")
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = "Yuborish",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}
