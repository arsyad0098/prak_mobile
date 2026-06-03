package com.example.arsyadapps.Message

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.arsyadapps.Message.tutorial.TutorialMessageActivity
import com.example.arsyadapps.R
import com.example.arsyadapps.databinding.FragmentMessageBinding
import kotlin.jvm.java

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



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Message"
        }

            setHasOptionsMenu(true)


        val adapter = MessageAdapter(requireContext(), messageList)
        binding.listMessageItems.adapter = adapter
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.message_toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_tutorial -> {
                val intent = Intent(requireContext(), TutorialMessageActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}