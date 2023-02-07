package com.datasoft.log_in;

import androidx.cardview.widget.CardView;

import com.datasoft.log_in.Models.Notes;

public interface NotesClick {
    void onClick(Notes notes);
    void onLongClick(Notes notes, CardView cardView);
}
