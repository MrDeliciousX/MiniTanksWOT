package com.mrdelicious.minitankswot.rosters;

import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;

@Dao
public interface RosterDao {
    @Query("SELECT * FROM rostersPattern")
    List<Roster> getRosterContent();
}
