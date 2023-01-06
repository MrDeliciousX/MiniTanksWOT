package com.mrdelicious.minitankswot.cards;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.mrdelicious.minitankswot.cards.crits.Crit;
import com.mrdelicious.minitankswot.cards.crits.CritDao;

@Database(entities = {Crit.class}, version = 1)
public abstract class CardsDatabase extends RoomDatabase {
    public abstract CritDao critDao();
}
