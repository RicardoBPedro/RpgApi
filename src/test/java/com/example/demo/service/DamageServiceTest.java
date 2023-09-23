package com.example.demo.service;

import com.example.demo.model.Character;
import com.example.demo.model.Damage;
import com.example.demo.model.DamageType;
import com.example.demo.model.Mob;
import com.example.demo.model.interfaces.Battleble;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class DamageServiceTest {

    @InjectMocks
    private DamageService damageService;

    @Test
    public void calculateDamage_shouldMiss_whenDefensivePowerIsGreaterThanAttackPower_And_HaNoDamageBonuses() {
        final long dmgPower = 1000L;
        final long defensivePower = 1001L;

        Battleble character = Mockito.mock(Character.class);
        Mockito.when(character.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(character.getCriticalDamageRate()).thenReturn(0);
        Mockito.when(character.getPiercingDamageRate()).thenReturn(0);
        Mockito.when(character.getExcellentDamageRate()).thenReturn(0);
        Mockito.when(character.getDoubleDamageRate()).thenReturn(0);
        Mockito.when(character.getTripleDamageRate()).thenReturn(0);

        Battleble mob = Mockito.mock(Mob.class);
        Mockito.when(mob.getDefensivePower()).thenReturn(defensivePower);

        Damage result = damageService.calculateDamage(character, mob);

        Mockito.verify(character, Mockito.times(1)).getDamagePower();
        Mockito.verify(mob, Mockito.times(1)).getDefensivePower();
        assertThat(result.getDamage()).isZero();
        assertThat(result.getDamageType()).isEqualTo(DamageType.MISS);
    }

    @Test
    public void calculateDamage_shouldMiss_whenDefensiveAndAttackPowerAreEquals_And_HaNoDamageBonuses() {
        final long dmgPower = 1000L;
        final long defensivePower = 1000L;

        Battleble character = Mockito.mock(Character.class);
        Mockito.when(character.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(character.getCriticalDamageRate()).thenReturn(0);
        Mockito.when(character.getPiercingDamageRate()).thenReturn(0);
        Mockito.when(character.getExcellentDamageRate()).thenReturn(0);
        Mockito.when(character.getDoubleDamageRate()).thenReturn(0);
        Mockito.when(character.getTripleDamageRate()).thenReturn(0);

        Battleble mob = Mockito.mock(Mob.class);
        Mockito.when(mob.getDefensivePower()).thenReturn(defensivePower);

        Damage result = damageService.calculateDamage(character, mob);

        Mockito.verify(character, Mockito.times(1)).getDamagePower();
        Mockito.verify(mob, Mockito.times(1)).getDefensivePower();
        assertThat(result.getDamage()).isZero();
        assertThat(result.getDamageType()).isEqualTo(DamageType.MISS);
    }

    @Test
    public void calculateDamage_shouldHitBasic_whenDefensivePowerIsLowerThanAttackPower() {
        final long dmgPower = 1000L;
        final long defensivePower = 999L;

        Battleble character = Mockito.mock(Character.class);
        Mockito.when(character.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(character.getCriticalDamageRate()).thenReturn(0);
        Mockito.when(character.getPiercingDamageRate()).thenReturn(0);
        Mockito.when(character.getExcellentDamageRate()).thenReturn(0);
        Mockito.when(character.getDoubleDamageRate()).thenReturn(0);
        Mockito.when(character.getTripleDamageRate()).thenReturn(0);

        Battleble mob = Mockito.mock(Mob.class);
        Mockito.when(mob.getDefensivePower()).thenReturn(defensivePower);

        Damage result = damageService.calculateDamage(character, mob);

        Mockito.verify(character, Mockito.times(1)).getDamagePower();
        Mockito.verify(mob, Mockito.times(1)).getDefensivePower();
        assertThat(result.getDamage()).isEqualTo(1L);
        assertThat(result.getDamageType()).isEqualTo(DamageType.BASIC);
    }

    @Test
    public void calculateDamage_shouldHitCritical_whenDefensivePowerIsGreaterThanAttackPower_And_Has100percentCriticalDamageRate() {
        final long dmgPower = 1000L;
        final long defensivePower = 1001L;

        Battleble character = Mockito.mock(Character.class);
        Mockito.when(character.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(character.getCriticalDamageRate()).thenReturn(100);
        Mockito.when(character.getPiercingDamageRate()).thenReturn(0);
        Mockito.when(character.getExcellentDamageRate()).thenReturn(0);
        Mockito.when(character.getDoubleDamageRate()).thenReturn(0);
        Mockito.when(character.getTripleDamageRate()).thenReturn(0);

        Battleble mob = Mockito.mock(Mob.class);
        Mockito.when(mob.getDefensivePower()).thenReturn(defensivePower);

        Damage result = damageService.calculateDamage(character, mob);

        Mockito.verify(character, Mockito.times(2)).getDamagePower();
        Mockito.verify(mob, Mockito.times(1)).getDefensivePower();
        assertThat(result.getDamage()).isEqualTo(dmgPower / 4 - 1);
        assertThat(result.getDamageType()).isEqualTo(DamageType.CRITICAL);
    }

    @Test
    public void calculateDamage_shouldHitCritical_whenDefensiveAndAttackPowerAreEquals_And_Has100percentCriticalDamageRate() {
        final long dmgPower = 1000L;
        final long defensivePower = 1000L;

        Battleble character = Mockito.mock(Character.class);
        Mockito.when(character.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(character.getCriticalDamageRate()).thenReturn(100);
        Mockito.when(character.getPiercingDamageRate()).thenReturn(0);
        Mockito.when(character.getExcellentDamageRate()).thenReturn(0);
        Mockito.when(character.getDoubleDamageRate()).thenReturn(0);
        Mockito.when(character.getTripleDamageRate()).thenReturn(0);

        Battleble mob = Mockito.mock(Mob.class);
        Mockito.when(mob.getDefensivePower()).thenReturn(defensivePower);

        Damage result = damageService.calculateDamage(character, mob);

        Mockito.verify(character, Mockito.times(2)).getDamagePower();
        Mockito.verify(mob, Mockito.times(1)).getDefensivePower();
        assertThat(result.getDamage()).isEqualTo(dmgPower / 4);
        assertThat(result.getDamageType()).isEqualTo(DamageType.CRITICAL);
    }

    @Test
    public void calculateDamage_shouldHitCritical_whenDefensivePowerIsLowerThanAttackPower_And_Has100percentCriticalDamageRate() {
        final long dmgPower = 1000L;
        final long defensivePower = 999L;

        Battleble character = Mockito.mock(Character.class);
        Mockito.when(character.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(character.getCriticalDamageRate()).thenReturn(100);
        Mockito.when(character.getPiercingDamageRate()).thenReturn(0);
        Mockito.when(character.getExcellentDamageRate()).thenReturn(0);
        Mockito.when(character.getDoubleDamageRate()).thenReturn(0);
        Mockito.when(character.getTripleDamageRate()).thenReturn(0);

        Battleble mob = Mockito.mock(Mob.class);
        Mockito.when(mob.getDefensivePower()).thenReturn(defensivePower);

        Damage result = damageService.calculateDamage(character, mob);

        Mockito.verify(character, Mockito.times(2)).getDamagePower();
        Mockito.verify(mob, Mockito.times(1)).getDefensivePower();
        assertThat(result.getDamage()).isEqualTo(dmgPower / 4 + 1);
        assertThat(result.getDamageType()).isEqualTo(DamageType.CRITICAL);
    }

    @Test
    public void calculateDamage_shouldHitPiercing_whenDefensivePowerIsGreaterThanAttackPower_And_Has100percentPiercingDamageRate() {
        final long dmgPower = 1000L;
        final long defensivePower = 1001L;

        Battleble character = Mockito.mock(Character.class);
        Mockito.when(character.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(character.getCriticalDamageRate()).thenReturn(0);
        Mockito.when(character.getPiercingDamageRate()).thenReturn(100);
        Mockito.when(character.getExcellentDamageRate()).thenReturn(0);
        Mockito.when(character.getDoubleDamageRate()).thenReturn(0);
        Mockito.when(character.getTripleDamageRate()).thenReturn(0);

        Battleble mob = Mockito.mock(Mob.class);
        Mockito.when(mob.getDefensivePower()).thenReturn(defensivePower);

        Damage result = damageService.calculateDamage(character, mob);

        Mockito.verify(character, Mockito.times(1)).getDamagePower();
        Mockito.verify(mob, Mockito.times(2)).getDefensivePower();
        assertThat(result.getDamage()).isEqualTo(dmgPower);
        assertThat(result.getDamageType()).isEqualTo(DamageType.PIERCING);
    }

    @Test
    public void calculateDamage_shouldHitPiercing_whenDefensiveAndAttackPowerAreEquals_And_Has100percentPiercingDamageRate() {
        final long dmgPower = 1000L;
        final long defensivePower = 1000L;

        Battleble character = Mockito.mock(Character.class);
        Mockito.when(character.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(character.getCriticalDamageRate()).thenReturn(0);
        Mockito.when(character.getPiercingDamageRate()).thenReturn(100);
        Mockito.when(character.getExcellentDamageRate()).thenReturn(0);
        Mockito.when(character.getDoubleDamageRate()).thenReturn(0);
        Mockito.when(character.getTripleDamageRate()).thenReturn(0);

        Battleble mob = Mockito.mock(Mob.class);
        Mockito.when(mob.getDefensivePower()).thenReturn(defensivePower);

        Damage result = damageService.calculateDamage(character, mob);

        Mockito.verify(character, Mockito.times(1)).getDamagePower();
        Mockito.verify(mob, Mockito.times(2)).getDefensivePower();
        assertThat(result.getDamage()).isEqualTo(dmgPower);
        assertThat(result.getDamageType()).isEqualTo(DamageType.PIERCING);
    }

    @Test
    public void calculateDamage_shouldHitPiercing_whenDefensivePowerIsLowerThanAttackPower_And_Has100percentPiercingDamageRate() {
        final long dmgPower = 1000L;
        final long defensivePower = 999L;

        Battleble character = Mockito.mock(Character.class);
        Mockito.when(character.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(character.getCriticalDamageRate()).thenReturn(0);
        Mockito.when(character.getPiercingDamageRate()).thenReturn(100);
        Mockito.when(character.getExcellentDamageRate()).thenReturn(0);
        Mockito.when(character.getDoubleDamageRate()).thenReturn(0);
        Mockito.when(character.getTripleDamageRate()).thenReturn(0);

        Battleble mob = Mockito.mock(Mob.class);
        Mockito.when(mob.getDefensivePower()).thenReturn(defensivePower);

        Damage result = damageService.calculateDamage(character, mob);

        Mockito.verify(character, Mockito.times(1)).getDamagePower();
        Mockito.verify(mob, Mockito.times(2)).getDefensivePower();
        assertThat(result.getDamage()).isEqualTo(dmgPower);
        assertThat(result.getDamageType()).isEqualTo(DamageType.PIERCING);
    }

    @Test
    public void calculateDamage_shouldHitExcellent_whenDefensivePowerIsGreaterThanAttackPower_And_Has100percentExcellentDamageRate() {
        final long dmgPower = 1000L;
        final long defensivePower = 1001L;

        Battleble character = Mockito.mock(Character.class);
        Mockito.when(character.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(character.getCriticalDamageRate()).thenReturn(0);
        Mockito.when(character.getPiercingDamageRate()).thenReturn(0);
        Mockito.when(character.getExcellentDamageRate()).thenReturn(100);
        Mockito.when(character.getDoubleDamageRate()).thenReturn(0);
        Mockito.when(character.getTripleDamageRate()).thenReturn(0);

        Battleble mob = Mockito.mock(Mob.class);
        Mockito.when(mob.getDefensivePower()).thenReturn(defensivePower);

        Damage result = damageService.calculateDamage(character, mob);

        Mockito.verify(character, Mockito.times(2)).getDamagePower();
        Mockito.verify(mob, Mockito.times(1)).getDefensivePower();
        assertThat(result.getDamage()).isEqualTo(dmgPower / 2 - 1);
        assertThat(result.getDamageType()).isEqualTo(DamageType.EXCELLENT);
    }

    @Test
    public void calculateDamage_shouldHitExcellent_whenDefensiveAndAttackPowerAreEquals_And_Has100percentExcellentDamageRate() {
        final long dmgPower = 1000L;
        final long defensivePower = 1000L;

        Battleble character = Mockito.mock(Character.class);
        Mockito.when(character.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(character.getCriticalDamageRate()).thenReturn(0);
        Mockito.when(character.getPiercingDamageRate()).thenReturn(0);
        Mockito.when(character.getExcellentDamageRate()).thenReturn(100);
        Mockito.when(character.getDoubleDamageRate()).thenReturn(0);
        Mockito.when(character.getTripleDamageRate()).thenReturn(0);

        Battleble mob = Mockito.mock(Mob.class);
        Mockito.when(mob.getDefensivePower()).thenReturn(defensivePower);

        Damage result = damageService.calculateDamage(character, mob);

        Mockito.verify(character, Mockito.times(2)).getDamagePower();
        Mockito.verify(mob, Mockito.times(1)).getDefensivePower();
        assertThat(result.getDamage()).isEqualTo(dmgPower / 2);
        assertThat(result.getDamageType()).isEqualTo(DamageType.EXCELLENT);
    }

    @Test
    public void calculateDamage_shouldHitExcellent_whenDefensivePowerIsLowerThanAttackPower_And_Has100percentExcellentDamageRate() {
        final long dmgPower = 1000L;
        final long defensivePower = 999L;

        Battleble character = Mockito.mock(Character.class);
        Mockito.when(character.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(character.getCriticalDamageRate()).thenReturn(0);
        Mockito.when(character.getPiercingDamageRate()).thenReturn(0);
        Mockito.when(character.getExcellentDamageRate()).thenReturn(100);
        Mockito.when(character.getDoubleDamageRate()).thenReturn(0);
        Mockito.when(character.getTripleDamageRate()).thenReturn(0);

        Battleble mob = Mockito.mock(Mob.class);
        Mockito.when(mob.getDefensivePower()).thenReturn(defensivePower);

        Damage result = damageService.calculateDamage(character, mob);

        Mockito.verify(character, Mockito.times(2)).getDamagePower();
        Mockito.verify(mob, Mockito.times(1)).getDefensivePower();
        assertThat(result.getDamage()).isEqualTo(dmgPower / 2 + 1);
        assertThat(result.getDamageType()).isEqualTo(DamageType.EXCELLENT);
    }

    @Test
    public void calculateDamage_shouldHitDouble_whenDefensivePowerIsGreaterThanAttackPower_And_Has100percentDoubleDamageRate() {
        final long dmgPower = 1000L;
        final long defensivePower = 1001L;

        Battleble character = Mockito.mock(Character.class);
        Mockito.when(character.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(character.getCriticalDamageRate()).thenReturn(0);
        Mockito.when(character.getPiercingDamageRate()).thenReturn(0);
        Mockito.when(character.getExcellentDamageRate()).thenReturn(0);
        Mockito.when(character.getDoubleDamageRate()).thenReturn(100);
        Mockito.when(character.getTripleDamageRate()).thenReturn(0);

        Battleble mob = Mockito.mock(Mob.class);
        Mockito.when(mob.getDefensivePower()).thenReturn(defensivePower);

        Damage result = damageService.calculateDamage(character, mob);

        Mockito.verify(character, Mockito.times(2)).getDamagePower();
        Mockito.verify(mob, Mockito.times(1)).getDefensivePower();
        assertThat(result.getDamage()).isEqualTo(dmgPower * 2 - defensivePower);
        assertThat(result.getDamageType()).isEqualTo(DamageType.DOUBLE);
    }

    @Test
    public void calculateDamage_shouldHitDouble_whenDefensiveAndAttackPowerAreEquals_And_Has100percentDoubleDamageRate() {
        final long dmgPower = 1000L;
        final long defensivePower = 1000L;

        Battleble character = Mockito.mock(Character.class);
        Mockito.when(character.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(character.getCriticalDamageRate()).thenReturn(0);
        Mockito.when(character.getPiercingDamageRate()).thenReturn(0);
        Mockito.when(character.getExcellentDamageRate()).thenReturn(0);
        Mockito.when(character.getDoubleDamageRate()).thenReturn(100);
        Mockito.when(character.getTripleDamageRate()).thenReturn(0);

        Battleble mob = Mockito.mock(Mob.class);
        Mockito.when(mob.getDefensivePower()).thenReturn(defensivePower);

        Damage result = damageService.calculateDamage(character, mob);

        Mockito.verify(character, Mockito.times(2)).getDamagePower();
        Mockito.verify(mob, Mockito.times(1)).getDefensivePower();
        assertThat(result.getDamage()).isEqualTo(dmgPower * 2 - defensivePower);
        assertThat(result.getDamageType()).isEqualTo(DamageType.DOUBLE);
    }

    @Test
    public void calculateDamage_shouldHitDouble_whenDefensivePowerIsLowerThanAttackPower_And_Has100percentDoubleDamageRate() {
        final long dmgPower = 1000L;
        final long defensivePower = 999L;

        Battleble character = Mockito.mock(Character.class);
        Mockito.when(character.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(character.getCriticalDamageRate()).thenReturn(0);
        Mockito.when(character.getPiercingDamageRate()).thenReturn(0);
        Mockito.when(character.getExcellentDamageRate()).thenReturn(0);
        Mockito.when(character.getDoubleDamageRate()).thenReturn(100);
        Mockito.when(character.getTripleDamageRate()).thenReturn(0);

        Battleble mob = Mockito.mock(Mob.class);
        Mockito.when(mob.getDefensivePower()).thenReturn(defensivePower);

        Damage result = damageService.calculateDamage(character, mob);

        Mockito.verify(character, Mockito.times(2)).getDamagePower();
        Mockito.verify(mob, Mockito.times(1)).getDefensivePower();
        assertThat(result.getDamage()).isEqualTo(dmgPower * 2 - defensivePower);
        assertThat(result.getDamageType()).isEqualTo(DamageType.DOUBLE);
    }

    @Test
    public void calculateDamage_shouldHitTriple_whenDefensivePowerIsGreaterThanAttackPower_And_Has100percentTripleDamageRate() {
        final long dmgPower = 1000L;
        final long defensivePower = 1001L;

        Battleble character = Mockito.mock(Character.class);
        Mockito.when(character.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(character.getCriticalDamageRate()).thenReturn(0);
        Mockito.when(character.getPiercingDamageRate()).thenReturn(0);
        Mockito.when(character.getExcellentDamageRate()).thenReturn(0);
        Mockito.when(character.getDoubleDamageRate()).thenReturn(0);
        Mockito.when(character.getTripleDamageRate()).thenReturn(100);

        Battleble mob = Mockito.mock(Mob.class);
        Mockito.when(mob.getDefensivePower()).thenReturn(defensivePower);

        Damage result = damageService.calculateDamage(character, mob);

        Mockito.verify(character, Mockito.times(2)).getDamagePower();
        Mockito.verify(mob, Mockito.times(1)).getDefensivePower();
        assertThat(result.getDamage()).isEqualTo(dmgPower * 3 - defensivePower);
        assertThat(result.getDamageType()).isEqualTo(DamageType.TRIPLE);
    }

    @Test
    public void calculateDamage_shouldHitTriple_whenDefensiveAndAttackPowerAreEquals_And_Has100percentTripleDamageRate() {
        final long dmgPower = 1000L;
        final long defensivePower = 1000L;

        Battleble character = Mockito.mock(Character.class);
        Mockito.when(character.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(character.getCriticalDamageRate()).thenReturn(0);
        Mockito.when(character.getPiercingDamageRate()).thenReturn(0);
        Mockito.when(character.getExcellentDamageRate()).thenReturn(0);
        Mockito.when(character.getDoubleDamageRate()).thenReturn(0);
        Mockito.when(character.getTripleDamageRate()).thenReturn(100);

        Battleble mob = Mockito.mock(Mob.class);
        Mockito.when(mob.getDefensivePower()).thenReturn(defensivePower);

        Damage result = damageService.calculateDamage(character, mob);

        Mockito.verify(character, Mockito.times(2)).getDamagePower();
        Mockito.verify(mob, Mockito.times(1)).getDefensivePower();
        assertThat(result.getDamage()).isEqualTo(dmgPower * 3 - defensivePower);
        assertThat(result.getDamageType()).isEqualTo(DamageType.TRIPLE);
    }

    @Test
    public void calculateDamage_shouldHitTriple_whenDefensivePowerIsLowerThanAttackPower_And_Has100percentTripleDamageRate() {
        final long dmgPower = 1000L;
        final long defensivePower = 999L;

        Battleble character = Mockito.mock(Character.class);
        Mockito.when(character.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(character.getCriticalDamageRate()).thenReturn(0);
        Mockito.when(character.getPiercingDamageRate()).thenReturn(0);
        Mockito.when(character.getExcellentDamageRate()).thenReturn(0);
        Mockito.when(character.getDoubleDamageRate()).thenReturn(0);
        Mockito.when(character.getTripleDamageRate()).thenReturn(100);

        Battleble mob = Mockito.mock(Mob.class);
        Mockito.when(mob.getDefensivePower()).thenReturn(defensivePower);

        Damage result = damageService.calculateDamage(character, mob);

        Mockito.verify(character, Mockito.times(2)).getDamagePower();
        Mockito.verify(mob, Mockito.times(1)).getDefensivePower();
        assertThat(result.getDamage()).isEqualTo(dmgPower * 3 - defensivePower);
        assertThat(result.getDamageType()).isEqualTo(DamageType.TRIPLE);
    }

    @Test
    public void calculateDamage_shouldStackDamage_whenMoreThanOneDamageSuccessRate() {
        // IMPORTANT: For this test, DamagePower and DefensivePower must be equals
        final long dmgPower = 1000L;
        final long defensivePower = 1000L;

        Battleble mob = Mockito.mock(Mob.class);
        Mockito.when(mob.getDefensivePower()).thenReturn(defensivePower);

        Battleble character = Mockito.mock(Character.class);
        Mockito.when(character.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(character.getCriticalDamageRate()).thenReturn(0);
        Mockito.when(character.getPiercingDamageRate()).thenReturn(0);
        Mockito.when(character.getExcellentDamageRate()).thenReturn(0);
        Mockito.when(character.getDoubleDamageRate()).thenReturn(0);
        Mockito.when(character.getTripleDamageRate()).thenReturn(0);
        Damage basicDamage = damageService.calculateDamage(character, mob);

        Mockito.when(character.getCriticalDamageRate()).thenReturn(100);
        Damage stackedCriticalDamage = damageService.calculateDamage(character, mob);

        Mockito.when(character.getPiercingDamageRate()).thenReturn(100);
        Damage stackedPiercingDamage = damageService.calculateDamage(character, mob);

        Mockito.when(character.getExcellentDamageRate()).thenReturn(100);
        Damage stackedExcellentDamage = damageService.calculateDamage(character, mob);

        Mockito.when(character.getDoubleDamageRate()).thenReturn(100);
        Damage stackedDoubleDamage = damageService.calculateDamage(character, mob);

        Mockito.when(character.getTripleDamageRate()).thenReturn(100);
        Damage stackedTripleDamage = damageService.calculateDamage(character, mob);

        long basic = basicDamage.getDamage();
        long stackedCritical = stackedCriticalDamage.getDamage();
        long stackedPiercing = stackedPiercingDamage.getDamage();
        long stackedExcellent = stackedExcellentDamage.getDamage();
        long stackedDoubleDmg = stackedDoubleDamage.getDamage();
        long stackedTriple = stackedTripleDamage.getDamage();

        Battleble criticalCharacter = Mockito.mock(Character.class);
        Mockito.when(criticalCharacter.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(criticalCharacter.getCriticalDamageRate()).thenReturn(100);
        Mockito.when(criticalCharacter.getPiercingDamageRate()).thenReturn(0);
        Mockito.when(criticalCharacter.getExcellentDamageRate()).thenReturn(0);
        Mockito.when(criticalCharacter.getDoubleDamageRate()).thenReturn(0);
        Mockito.when(criticalCharacter.getTripleDamageRate()).thenReturn(0);
        Damage criticalDamage = damageService.calculateDamage(criticalCharacter, mob);
        long critical = criticalDamage.getDamage();

        Battleble piercingCharacter = Mockito.mock(Character.class);
        Mockito.when(piercingCharacter.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(piercingCharacter.getCriticalDamageRate()).thenReturn(0);
        Mockito.when(piercingCharacter.getPiercingDamageRate()).thenReturn(100);
        Mockito.when(piercingCharacter.getExcellentDamageRate()).thenReturn(0);
        Mockito.when(piercingCharacter.getDoubleDamageRate()).thenReturn(0);
        Mockito.when(piercingCharacter.getTripleDamageRate()).thenReturn(0);
        Damage piercingDamage = damageService.calculateDamage(piercingCharacter, mob);
        long piercing = piercingDamage.getDamage();

        Battleble excellentCharacter = Mockito.mock(Character.class);
        Mockito.when(excellentCharacter.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(excellentCharacter.getCriticalDamageRate()).thenReturn(0);
        Mockito.when(excellentCharacter.getPiercingDamageRate()).thenReturn(0);
        Mockito.when(excellentCharacter.getExcellentDamageRate()).thenReturn(100);
        Mockito.when(excellentCharacter.getDoubleDamageRate()).thenReturn(0);
        Mockito.when(excellentCharacter.getTripleDamageRate()).thenReturn(0);
        Damage excellentDamage = damageService.calculateDamage(excellentCharacter, mob);
        long excellent = excellentDamage.getDamage();

        Battleble doubleCharacter = Mockito.mock(Character.class);
        Mockito.when(doubleCharacter.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(doubleCharacter.getCriticalDamageRate()).thenReturn(0);
        Mockito.when(doubleCharacter.getPiercingDamageRate()).thenReturn(0);
        Mockito.when(doubleCharacter.getExcellentDamageRate()).thenReturn(0);
        Mockito.when(doubleCharacter.getDoubleDamageRate()).thenReturn(100);
        Mockito.when(doubleCharacter.getTripleDamageRate()).thenReturn(0);
        Damage doubleDamage = damageService.calculateDamage(doubleCharacter, mob);
        long doubleDmg = doubleDamage.getDamage();

        Battleble tripleCharacter = Mockito.mock(Character.class);
        Mockito.when(tripleCharacter.getDamagePower()).thenReturn(dmgPower);
        Mockito.when(tripleCharacter.getCriticalDamageRate()).thenReturn(0);
        Mockito.when(tripleCharacter.getPiercingDamageRate()).thenReturn(0);
        Mockito.when(tripleCharacter.getExcellentDamageRate()).thenReturn(0);
        Mockito.when(tripleCharacter.getDoubleDamageRate()).thenReturn(0);
        Mockito.when(tripleCharacter.getTripleDamageRate()).thenReturn(100);
        Damage tripleDamage = damageService.calculateDamage(tripleCharacter, mob);
        long triple = tripleDamage.getDamage();

        assertThat(stackedCritical).isEqualTo(basic + critical);
        assertThat(stackedPiercing).isEqualTo(basic + critical + piercing);
        assertThat(stackedExcellent).isEqualTo(basic + critical + piercing + excellent);
        assertThat(stackedDoubleDmg).isEqualTo(basic + critical + piercing + excellent + doubleDmg);
        assertThat(stackedTriple).isEqualTo(basic + critical + piercing + excellent + doubleDmg + triple);
    }
}
