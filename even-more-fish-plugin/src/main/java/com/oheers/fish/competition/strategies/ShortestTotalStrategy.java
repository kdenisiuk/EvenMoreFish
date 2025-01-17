package com.oheers.fish.competition.strategies;


import com.oheers.fish.competition.Competition;
import com.oheers.fish.competition.CompetitionEntry;
import com.oheers.fish.competition.CompetitionStrategy;
import com.oheers.fish.competition.leaderboard.Leaderboard;
import com.oheers.fish.config.messages.ConfigMessage;
import com.oheers.fish.config.messages.Message;
import com.oheers.fish.fishing.items.Fish;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ShortestTotalStrategy implements CompetitionStrategy {
    @Override
    public void applyToLeaderboard(Fish fish, Player fisher, Leaderboard leaderboard, Competition competition) {
        CompetitionEntry entry = leaderboard.getEntry(fisher.getUniqueId());
        float increaseAmount = fish.getLength();

        if (entry != null) {
            entry.incrementValue(increaseAmount);
            leaderboard.updateEntry(entry);
        } else {
            CompetitionEntry newEntry = new CompetitionEntry(fisher.getUniqueId(), fish, competition.getCompetitionType());
            newEntry.incrementValue(increaseAmount - 1); // Adjust for the first entry
            leaderboard.addEntry(newEntry);
        }
    }

    @Override
    public Message getSingleConsoleLeaderboardMessage(@NotNull Message message, @NotNull CompetitionEntry entry) {
        message.setMessage(ConfigMessage.LEADERBOARD_SHORTEST_TOTAL);
        message.setAmount(getDecimalFormat().format(entry.getValue()));
        return message;
    }

    @Override
    public Message getSinglePlayerLeaderboard(@NotNull Message message, @NotNull CompetitionEntry entry) {
        message.setMessage(ConfigMessage.LEADERBOARD_SHORTEST_TOTAL);
        message.setAmount(getDecimalFormat().format(entry.getValue()));
        return message;
    }
}
