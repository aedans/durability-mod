package io.github.aedans;

public record DurabilityConfig(Modifier durability, Modifier speed) {
  @Override
  public Modifier durability() {
    return durability == null ? new Modifier(1, 1) : durability;
  }

  @Override
  public Modifier speed() {
    return speed == null ? new Modifier(1, 1) : speed;
  }

  public record Modifier(int multiply, int divide) {
    @SuppressWarnings("unused")
    public Modifier() {
      this(1, 1);
    }
  }
}
