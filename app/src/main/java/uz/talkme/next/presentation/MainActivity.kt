package uz.talkme.next.presentation

import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.AndroidEntryPoint
import uz.talkme.next.databinding.ActivityMainBinding

/** The single Activity hosts one voice-conversation destination. */
@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContentView(ActivityMainBinding.inflate(layoutInflater).root)
    }
}
