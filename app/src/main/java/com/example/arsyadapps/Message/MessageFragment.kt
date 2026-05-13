package com.example.arsyadapps.Message

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.arsyadapps.R
import com.example.arsyadapps.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {
    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    private val messageList = listOf(
        MessageModel("Alya", "Halo! Apa kabar?", "https://randomuser.me/api/portraits/women/20.jpg"),
        MessageModel("Budi", "Sudah makan?", "https://randomuser.me/api/portraits/men/33.jpg"),
        MessageModel("Citra", "Jangan lupa tugasnya ya!", "https://randomuser.me/api/portraits/women/12.jpg"),
        MessageModel("Dika", "Besok kita rapat jam 9", "https://randomuser.me/api/portraits/men/32.jpg"),
        MessageModel("Eka", "Nice job kemarin!", "https://randomuser.me/api/portraits/men/35.jpg"),
        MessageModel("Fajar", "Lagi ngapain?", "https://randomuser.me/api/portraits/men/10.jpg"),
        MessageModel("Gita", "Boleh minta tolong?", "https://randomuser.me/api/portraits/women/13.jpg"),
        MessageModel("Hana", "Lihat email ya", "https://randomuser.me/api/portraits/women/30.jpg"),
        MessageModel("Irfan", "Oke noted", "https://randomuser.me/api/portraits/men/32.jpg"),
        MessageModel("Joko", "Sampai jumpa besok", "https://randomuser.me/api/portraits/women/16.jpg")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.title = "Message"
        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter
    }
}