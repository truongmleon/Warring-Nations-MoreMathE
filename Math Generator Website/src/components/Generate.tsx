let answers: number[] = [];

const checkAnswer = (answer: string) => {
  const result: string = (document.querySelector('input[name="answer"]:checked') as HTMLInputElement).value;

  result == answer ? window.alert("Correct!") : window.alert("You suck!");
  
}

const arithmetic = () => {
  const operations: string[] = [" + ", " + ", " - ", " - ", " * ", " / "];
  const randomOperations: string[] = [operations[Math.floor(Math.random() * operations.length)], operations[Math.floor(Math.random() * operations.length)], operations[Math.floor(Math.random() * operations.length)]];
  let inPlace = [];
  let random = Math.floor(Math.random() * 4);
  let result = "69";
  let answer = 0;

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

  return [answer.toString(), result];
}

const geometry = () => {
  let result = [""];
  return result;
}

const algebruh = () => {
  let result = [""];
  return result;
}

const calculus = () => {
  let result = [""];
  return result;
}

const abstract = () => {
  let result = [""];
  return result;
}

const stages: string[] = ["arithmetic", "geometry", "algebruh", "calculus", "abstract"];
const stageFunctions: string[][] = [arithmetic(), geometry(), algebruh(), calculus(), abstract()];

const Generate = (props: any) => {
    const stage: string = props.stage;
    let result: string[] = [];

    for (let i: number = 0; i < stages.length; i++) {
      if (stages[i] === stage) {
        result = stageFunctions[i];
        break;
      }
    }

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
      </div>
       </div>
    )
  }
  
  export default Generate;