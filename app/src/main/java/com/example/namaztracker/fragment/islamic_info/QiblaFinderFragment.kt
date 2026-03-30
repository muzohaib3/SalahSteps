import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.namaztracker.databinding.FragmentQiblaFinderBinding
import com.example.namaztracker.fragment.islamic_info.qibla.QiblaManager
import kotlin.math.abs

class QiblaFinderFragment : Fragment() {

    private var _binding: FragmentQiblaFinderBinding? = null
    private val binding get() = _binding!!

    private lateinit var qiblaManager: QiblaManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQiblaFinderBinding.inflate(inflater, container, false)

        qiblaManager = QiblaManager(requireContext()) { rotationAngle ->

            if (_binding != null) {
                binding.ivNeedle.rotation = rotationAngle
                binding.tvDirection.text = "${abs(rotationAngle.toInt())}°"
            }
        }

        // Dummy location (Karachi)
        qiblaManager.updateLocation(24.8607, 67.0011)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        qiblaManager.start()
    }

    override fun onPause() {
        super.onPause()
        qiblaManager.stop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}