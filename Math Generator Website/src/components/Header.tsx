const Header = () => {
  return (
    <div className="">
      <h1 id="main">Warring Nations Math Maker</h1>

      <label htmlFor="stages">Choose a stage: </label>

    <select name="stages" id="stages">
      <option value="arithmetic">Arithmetic</option>
      <option value="geometry">Geometry</option>
      <option value="algebruh">Algebruh</option>
      <option value="calculus">Calculus</option>
      <option value="abstract">Abstract</option>
    </select>

    <button>Go</button>
    </div>
  )
}


export default Header;