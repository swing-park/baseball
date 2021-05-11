import { useReducer } from "react";
import InningInfo from "./InningInfo";
import PitchButton from "./PitchButton";
import GameDisplay from "./GameDisplay/GameDisplay";
import BallCountBoard from "./BallCountBoard/BallCountBoard";
import { BACKGROUND_URL } from "@/Utils/const";
import { GamePlayground as S } from "@/Components/Game/GameStyles";

const reducer = (state, action) => {
  const initialBallState = {
    strike: 0,
    ball: 0,
    out: 0,
  };

  switch (action.type) {
    case "STRIKE_OUT":
      return { ...state, strike: 0, out: state.out + 1 };
    case "STRIKE":
      return { ...state, strike: state.strike + 1 };
    case "FOUR_BALL":
      // 추가로 1루타 액션 필요
      return { ...state, ball: 0 };
    case "BALL":
      return { ...state, ball: state.ball + 1 };
    case "THREE_OUT":
      let inningState = { ...state, ...initialBallState };
      if (state.inningCount === "초") {
        inningState.inningCount = "말";
      } else {
        inningState.inning = state.inning + 1;
        inningState.inningCount = "초";
      }
      inningState.isDefense = !state.isDefense;
      return { ...inningState };
    default:
      return;
  }
};

const GamePlayground = () => {
  const initialState = {
    strike: 2,
    ball: 0,
    out: 2,
    inning: 1,
    inningCount: "초",
    isDefense: true,
  };
  const [state, dispatch] = useReducer(reducer, initialState);

  return (
    <S.GamePlayground>
      <S.Background src={BACKGROUND_URL} />
      <BallCountBoard ballCount={state} dispatch={dispatch} />
      <InningInfo inningInfo={state} dispatch={dispatch} />
      <GameDisplay />
      <PitchButton dispatch={dispatch} />
    </S.GamePlayground>
  );
};

export default GamePlayground;
