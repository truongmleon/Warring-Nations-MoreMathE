const Generate = (props: any) => {
    const stage: string = props.stage;
    let image: string = "";
    let answers: number[] = [];
    return (  
      <div className="flex">
        <img width="200" src="https://miro.medium.com/v2/resize:fit:1400/1*L76A5gL6176UbMgn7q4Ybg.jpeg" alt="photo of math" />
      <form action="">
          <h2>Select an answer: </h2>
          <input type="radio" /> <br />
          <input type="radio" /> <br />
          <input type="radio" /> <br />
          <input type="radio" /> <br />
      </form>
  
      <button className="go">Enter</button>
      </div>
    )
  }
  
  export default Generate;