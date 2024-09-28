
type Modifier = any; // Replace with actual Modifier type definition

type Camouflage = Exterior;
type Flag = Exterior;

interface Exterior {
  id: string;
  name: string;
  icon: ImageSourcePropType;
  description?: string;
  costGold?: number;
  costCR?: number;
  modifiers?: Modifier[];
  type: string;
}

const ExteriorComponent: React.FC<Exterior> = ({
  id,
  name,
  icon,
  description,
  costGold,
  costCR,
  modifiers,
  type,
}) => {
  return (
    <div>
      <h1>{name}</h1>
      <img src={icon as unknown as string} alt={name} />
      {description && <p>{description}</p>}
      {costGold && <p>Cost in Gold: {costGold}</p>}
      {costCR && <p>Cost in CR: {costCR}</p>}
      {modifiers && (
        <ul>
          {modifiers.map((modifier, index) => (
            <li key={index}>{JSON.stringify(modifier)}</li>
          ))}
        </ul>
      )}
      <p>Type: {type}</p>
    </div>
  );
};

export default ExteriorComponent;

interface Modifiers {
  // Define the properties of Modifiers based on your requirements
}

interface Exterior {
  id: number;
  name: string;
  icon: string;
  description?: string;
  costGold?: number;
  costCR?: number;
  modifiers?: Modifiers;
  type: string;
  isFlag: boolean;
}

const fromJson = (json: any): Exterior => ({
  id: json.id,
  name: json.name,
  icon: json.icon,
  description: json.description,
  costGold: json.costGold,
  costCR: json.costCR,
  modifiers: json.modifiers ? fromModifiersJson(json.modifiers) : undefined,
  type: json.type,
  isFlag: json.type === 'Flags',
});

const fromModifiersJson = (json: any): Modifiers => {
  // Implement the conversion logic for Modifiers based on your requirements
  return {} as Modifiers; // Replace with actual implementation
};