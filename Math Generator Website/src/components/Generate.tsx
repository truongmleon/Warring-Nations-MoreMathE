let answers: number[] = [];

const checkAnswer = (answer: string) => {
  const result: string = (document.querySelector('input[name="answer"]:checked') as HTMLInputElement).value;
  const buttons: NodeListOf<HTMLElement> = document.getElementsByName('answer') as NodeListOf<HTMLElement>;
  result == answer ? window.alert("Correct!") : window.alert("You suck!");
  
  for (let i = 0; i < buttons.length; i++) {
    (buttons[i] as HTMLButtonElement).disabled = true;
  }

  (document.getElementById('hide') as HTMLElement).style.display = 'block';
}

const arithmetic = () => {
  const operations: string[] = [" + ", " + ", " - ", " - ", " * ", " / "];
  const randomOperations: string[] = [operations[Math.floor(Math.random() * operations.length)], operations[Math.floor(Math.random() * operations.length)], operations[Math.floor(Math.random() * operations.length)]];
  let link: string = "https://www.wolframalpha.com/input?i=";
  let toLink: string = ""; 
  let result: string = "69";
  let inPlace: number[] = [];
  let random: number = Math.floor(Math.random() * 4);
  let answer: number = 0;

  for (let i: number = 0; i < randomOperations.length; i++) {
    result += randomOperations[i];
    for (let j: number = 0; j < 3; j++) {
      result += Math.floor(Math.random() * 9 + 1).toString();
    }
  }

  answer = eval(result).toFixed(2);
  answers[random] = answer;
  inPlace.push(random);

  while (inPlace.length < 4) {
    random = Math.floor(Math.random() * 4);

    while (inPlace.includes(random)) {
      random = Math.floor(Math.random() * 4);
    }

    inPlace.push(random);

    let resultArr = result.split(" ");
    resultArr[2] = random + 1 + resultArr[2][1] + resultArr[2][2];
    resultArr[4] = Math.floor(Math.random() * 8) + 2 + resultArr[4][1] + resultArr[4][2];
    resultArr[4] = Math.floor(Math.random() * 6) + 2 + resultArr[6][1] + resultArr[6][2];
    answers[random] = eval(resultArr.join("")).toFixed(2);
  }

  toLink = result;

  toLink = toLink.replaceAll(" + ", "+%2B+");
  toLink = toLink.replaceAll(" - ", "+-+");
  toLink = toLink.replaceAll(" * ", "+*+");
  toLink = toLink.replaceAll(" / ", "+%2F+");

  return [answer.toString(), result, link + toLink];
}

const Generate = () => {
    let result: string[] = [];

    result = arithmetic();

    return (  
       <div>
        <p><span className="math display">\[{result[1]}\]</span></p>

      <div className="flex">
      <form id="answer-sheet" action="">
          <h2 id="answer-title">Select an answer: </h2>
          <input type="radio" name="answer" value={answers[0]} /> 
          <label className="answers">{answers[0]}</label> 
          <input type="radio" name="answer" value={answers[1]} /> 
          <label className="answers">{answers[1]}</label> <br />
          <input type="radio" name="answer" value={answers[2]} /> 
          <label className="answers">{answers[2]}</label> <br />
          <input type="radio" name="answer" value={answers[3]} />
          <label className="answers">{answers[3]}</label> <br />
      </form>
  
      <button onClick={() => checkAnswer(result[0])} className="go">Enter</button>

      <div>
  
      </div>
      </div>
      <div id="hide">
      <h2 className="answer">Correct Answer: {result[0]}</h2>
      <h2 className="answer"><a target="_blank" href={result[2]}>Solution</a></h2>
      </div>
       </div>
    )
  }
  
  export default Generate;