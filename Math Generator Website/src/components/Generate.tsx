let answers: number[] = [];

const arithmetic = () => {
  const operations: string[] = [" + ", " + ", " - ", " - ", " * ", " / "];
  const randomOperations: string[] = [operations[Math.floor(Math.random() * operations.length)], operations[Math.floor(Math.random() * operations.length)], operations[Math.floor(Math.random() * operations.length)]];
  let result = "69";
  let answer = 0;

  for (let i: number = 0; i < randomOperations.length; i++) {
    result += randomOperations[i];
    for (let j: number = 0; j < 3; j++) {
      result += Math.floor(Math.random() * 9 + 1).toString();
    }
  }
  
  answer = eval(result).toFixed(2);
  answers[Math.floor(Math.random() * 4)] = answer;

  return result;
}

const geometry = () => {
  let result = "";
  return result;
}

const algebruh = () => {
  let result = "";
  return result;
}

const calculus = () => {
  let result = "";
  return result;
}

const abstract = () => {
  let result = "";
  return result;
}

const stages: string[] = ["arithmetic", "geometry", "algebruh", "calculus", "abstract"];
const stageFunctions: string[] = [arithmetic(), geometry(), algebruh(), calculus(), abstract()];

const Generate = (props: any) => {
    const stage: string = props.stage;
    let result: string = "";

    for (let i: number = 0; i < stages.length; i++) {
      if (stages[i] === stage) {
        result = stageFunctions[i];
        break;
      }
    }

    return (  
       <div>
        <p><span className="math display">\[{result}\]</span></p>

      <div className="flex">
      <form id="answers" action="">
          <h2 id="answer-title">Select an answer: </h2>
          <input type="radio" name="answer" value="" /> {answers[0]} <br />
          <input type="radio" name="answer" value="" /> {answers[1]} <br />
          <input type="radio" name="answer" value="" /> {answers[2]} <br />
          <input type="radio" name="answer" value="" /> {answers[3]} <br />
      </form>
  
      <button className="go">Enter</button>
      </div>
       </div>
    )
  }
  
  export default Generate;