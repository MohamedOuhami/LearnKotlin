package com.v01d.learnkotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.findNavController

class MessageFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_message, container, false)

        // Getting the button
        val sendBtn = view.findViewById<Button>(R.id.sendBtn)
        val messagetext = view.findViewById<EditText>(R.id.message)

        sendBtn.setOnClickListener {
            // Corrected: Get the text from the EditText
            val messageText = messagetext.text.toString()

            // Create a bundle and store the text
            val action = MessageFragmentDirections.actionMessageFragmentToEncryptFragment(messageText)


            view.findNavController().navigate(action)
        }

        return view
    }


}