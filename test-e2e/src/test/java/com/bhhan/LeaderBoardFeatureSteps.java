package com.bhhan;

import com.bhhan.testutils.beans.LeaderBoardPosition;
import cucumber.api.java.ko.그러면;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by hbh5274@gmail.com on 2020-07-22
 * Github : http://github.com/bhhan5274
 */
public class LeaderBoardFeatureSteps {
    private MultiplicationFeatureSteps mSteps;

    public LeaderBoardFeatureSteps(MultiplicationFeatureSteps mSteps) {
        this.mSteps = mSteps;
    }

    @그러면("^사용자 ([^\\s]+)가 리더보드에서 (\\d+)등이 된다$")
    public void 사용자가_리더보드에서_등수에_오른다(final String user, final int position) throws Throwable {
        Thread.currentThread().sleep(500);
        List<LeaderBoardPosition> leaderBoard = mSteps.getApp().getLeaderBoard();
        assertThat(leaderBoard).isNotEmpty();
        long userId = leaderBoard.get(position - 1).getUserId();
        String userAlias = mSteps.getApp().getUser(userId).getAlias();
        assertThat(userAlias).isEqualTo(user);
    }
}
