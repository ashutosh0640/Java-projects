import React, { useRef, useState } from 'react';

function Stopwatch() {
  const [count, setCount] = useState(0);
  const timerRef = useRef(null);

  const startTimer = () => {
    console.log("current timeRef: ",timerRef.current);
    if (timerRef.current) return; // Prevent multiple timers
    timerRef.current = setInterval(() => {
      setCount((prev) => prev + 1);
    }, 1000);
  };

  const stopTimer = () => {
    console.log("stop timeRef: ",timerRef.current);
    clearInterval(timerRef.current);
    timerRef.current = null; // Reset the ref
  };

  const resetCount = () => {
    setCount(0);
    stopTimer();
  }

  return (
    <div>
      <h1>{count}</h1>
      <button onClick={startTimer}>Start Timer</button>
      <button onClick={stopTimer}>Stop Timer</button>
      <button onClick={resetCount}>Reset</button>
    </div>
  );
}

export default Stopwatch;
